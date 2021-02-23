package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.dto.CategoriaDTO;
import com.celso.springmc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){ //Busca Categoria por ID
       Categoria obj = categoriaService.find(id);

        return ResponseEntity.ok(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity <List<CategoriaDTO>> findAll(){  // Lista todas Categorias
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(CategoriaDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(listDTO);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)   //Paginação com parãmetro opcionais na Requisição
    public ResponseEntity <Page<CategoriaDTO>> findPage(@RequestParam(value = "page" , defaultValue = "0") Integer page,
                                                        @RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
                                                        @RequestParam(value = "order",defaultValue = "nome") String orderBy,
                                                        @RequestParam(value = "direction",defaultValue = "ASC") String direction){

        Page<Categoria> listPage = categoriaService.findPage(page,linesPerPage,orderBy,direction);
        Page<CategoriaDTO> listDTO = listPage.map(CategoriaDTO::new);

        return ResponseEntity.ok(listDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){ //Insere uma Categoria
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id){ //Atualiza uma Categoria
        obj.setId(id);
        categoriaService.update(obj);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){ //Deleta uma Categoria
        categoriaService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
