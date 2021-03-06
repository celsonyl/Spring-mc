package com.celso.springmc.services;

import com.celso.springmc.domain.Cidade;
import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.Endereco;
import com.celso.springmc.domain.dto.ClienteDTO;
import com.celso.springmc.domain.dto.ClienteNewDTO;
import com.celso.springmc.domain.enums.Perfil;
import com.celso.springmc.domain.enums.TipoCliente;
import com.celso.springmc.repositories.ClienteRepository;
import com.celso.springmc.repositories.EnderecoRepository;
import com.celso.springmc.security.UserSS;
import com.celso.springmc.services.exceptions.AuthorizationException;
import com.celso.springmc.services.exceptions.DataIntegrityException;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.cliente.profile}")
    private String prefix;

    public Cliente find(Integer id) { //Busca um Cliente por ID
        UserSS userSS = UserService.authenticated();
        if(userSS == null || !userSS.hasRole(Perfil.ADMIN) && !id.equals(userSS.getId())){
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id + " Tipo: " + Cliente.class.getName()));
    }
    
    public Cliente findByEmail(String email){
        UserSS userSS = UserService.authenticated();
        if(userSS == null || !userSS.hasRole(Perfil.ADMIN) && !email.equals(userSS.getUsername())){
            throw new AuthorizationException("Acesso negado");
        }

        Cliente obj = clienteRepository.findByEmail(email);
        if(obj == null){
            throw new ObjectNotFoundException("Email não encontrado!");
        }
        return obj;

    }

    public List<Cliente> findAll() { //Lista todos os Clientes
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public void update(Cliente obj) { // Atualiza Nome e Email de um Cliente
        Cliente newOBJ = find(obj.getId());
        updateData(newOBJ, obj);
        clienteRepository.save(newOBJ);
    }

    public void delete(Integer id) { //Delete um Cliente
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é posivel deletar uma Cliente que possui Pedidos!");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) { //Paginação com parãmetros opcionais na Requisição
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO obj) {
        return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null,null);
    }

    public Cliente fromDTO(ClienteNewDTO objDTO) {
        Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()),bCryptPasswordEncoder.encode(objDTO.getSenha()));
        Cidade cidade = new Cidade(objDTO.getCidadeID(), null, null);
        Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDTO.getTelefone1());

        if (objDTO.getTelefone2() != null) {
            cliente.getTelefones().add(objDTO.getTelefone2());
        }
        if (objDTO.getTelefone3() != null) {
            cliente.getTelefones().add(objDTO.getTelefone3());
        }
        return cliente;
    }


    private void updateData(Cliente newOBJ, Cliente obj) {
        newOBJ.setNome(obj.getNome());
        newOBJ.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) throws IOException {
        UserSS userSS = UserService.authenticated();
        if(userSS == null){
            throw new AuthorizationException("Acesso negado");
        }
        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        String fileName = prefix + userSS.getId() + ".jpg";
        return s3Service.uploadFile(imageService.getInputStream(jpgImage,"jpg"),fileName,"image");

    }

}
