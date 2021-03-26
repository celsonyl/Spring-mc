package com.celso.springmc.services;

import com.celso.springmc.domain.Cidade;
import com.celso.springmc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findByEstado(Integer estadoId){
        List<Cidade> byEstado = cidadeRepository.findCidadeByEstadoId(estadoId);
        return byEstado;
    }

}
