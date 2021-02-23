package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
       Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id){
        obj.setId(id);
        categoriaService.update(obj);

        return ResponseEntity.noContent().build();
    }

}
