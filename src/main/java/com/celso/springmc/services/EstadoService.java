package com.celso.springmc.services;

import com.celso.springmc.domain.Estado;
import com.celso.springmc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll() { //Lista Estados

        return estadoRepository.findAllByOrderByNome();
    }
}
