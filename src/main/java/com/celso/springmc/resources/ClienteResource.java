package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.dto.CategoriaDTO;
import com.celso.springmc.domain.dto.ClienteDTO;
import com.celso.springmc.domain.dto.ClienteNewDTO;
import com.celso.springmc.services.ClienteService;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){ //Busca Cliente por ID
        Cliente obj = clienteService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){ //Lista todos os Clientes

        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDTO = list.stream().map(ClienteDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){ //Insere uma CategoriaDTO
        Cliente obj = clienteService.fromDTO(objDTO);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){ //Atualiza uma Cliente
        Cliente obj = clienteService.fromDTO(objDTO);
        obj.setId(id);
        clienteService.update(obj);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){ //Deleta um Cliente
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
