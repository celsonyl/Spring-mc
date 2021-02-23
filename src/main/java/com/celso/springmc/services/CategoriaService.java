package com.celso.springmc.services;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.repositories.CategoriaRepository;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Optional<Categoria> cat = categoriaRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj){
        if(obj == null){
            throw new ObjectNotFoundException("Categoria não existe! " + Categoria.class.getName());
        }
        return categoriaRepository.save(obj);
    }
}
