package com.celso.springmc.services;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria busca(int id){
        Optional<Categoria> cat = categoriaRepository.findById(id);
        return cat.orElse(null);
    }
}
