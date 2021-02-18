package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable int id){
       Categoria obj = categoriaService.busca(id);

        return ResponseEntity.ok(obj);
    }

}
