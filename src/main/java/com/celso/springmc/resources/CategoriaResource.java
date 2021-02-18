package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){
        List<Categoria> list = new ArrayList<>();
        Categoria c1 = new Categoria(1,"Celso");
        Categoria c2 = new Categoria(2,"Robertin");
        list.add(c1);
        list.add(c2);


        return list;
    }

}
