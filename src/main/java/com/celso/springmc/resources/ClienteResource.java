package com.celso.springmc.resources;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.services.ClienteService;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente obj = clienteService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
