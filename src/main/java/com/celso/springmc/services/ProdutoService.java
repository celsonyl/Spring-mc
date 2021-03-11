package com.celso.springmc.services;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.Produto;
import com.celso.springmc.repositories.CategoriaRepository;
import com.celso.springmc.repositories.ProdutoRepository;
import com.celso.springmc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Produto find(Integer id) { //Busca um Produto por ID
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado: " + id + " Class: +" + Produto.class.getName()));
    }


    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

    }


}
