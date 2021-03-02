package com.celso.springmc.services;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.dto.CategoriaDTO;
import com.celso.springmc.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){ //Procura Categoria por ID
        Optional<Categoria> cat = categoriaRepository.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){ //Lista todas as Categorias
        return categoriaRepository.findAll();

    }


    public Categoria insert(Categoria obj){ //Insere uma Categoria
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public void update(Categoria obj){ //Atualiza uma Categoria
        Categoria newOBJ = find(obj.getId());
        updateData(newOBJ,obj);
        categoriaRepository.save(newOBJ);
    }

    public void delete(Integer id){ //Deleta uma Categoria
        find(id);
        try {
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é posivel deletar uma Categoria que possui Produtos!");
        }
    }

    public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){ //Paginação com parãmetros opcionais na Requisição
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(),objDTO.getNome());
    }

    private void updateData(Categoria newOBJ, Categoria obj){
        newOBJ.setNome(obj.getNome());

    }
}
