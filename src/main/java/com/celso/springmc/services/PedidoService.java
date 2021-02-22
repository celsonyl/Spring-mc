package com.celso.springmc.services;

import com.celso.springmc.domain.Pedido;
import com.celso.springmc.repositories.PedidoRepository;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    public Pedido buscar(int id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado: "+id + "Class: +" +Pedido.class.getName()));
    }
}
