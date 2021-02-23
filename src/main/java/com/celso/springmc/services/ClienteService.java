package com.celso.springmc.services;

import com.celso.springmc.domain.Cliente;
import com.celso.springmc.repositories.ClienteRepository;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente busca(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: "+id + " Tipo: " +Cliente.class.getName()));
    }
}
