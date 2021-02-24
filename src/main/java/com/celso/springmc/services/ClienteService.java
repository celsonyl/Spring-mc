package com.celso.springmc.services;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.dto.ClienteDTO;
import com.celso.springmc.repositories.ClienteRepository;
import com.celso.springmc.services.exceptions.DataIntegrityException;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){ //Busca um Cliente por ID
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: "+id + " Tipo: " +Cliente.class.getName()));
    }

    public List<Cliente> findAll(){ //Lista todos os Clientes
    return clienteRepository.findAll();
    }

    public void update(Cliente obj){ // Atualiza Nome e Email de um Cliente
        Cliente newOBJ = find(obj.getId());
        updateData(newOBJ,obj);
        clienteRepository.save(newOBJ);
    }

    public void delete(Integer id){ //Delete um Cliente
        find(id);
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é posivel deletar uma Cliente que possui Pedidos!");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){ //Paginação com parãmetros opcionais na Requisição
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO obj){
       return new Cliente(obj.getId(),obj.getNome(),obj.getEmail(),null,null);
    }

    private void updateData(Cliente newOBJ,Cliente obj){
        newOBJ.setNome(obj.getNome());
        newOBJ.setEmail(obj.getEmail());
    }

}
