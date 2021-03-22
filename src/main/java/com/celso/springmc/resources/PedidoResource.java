package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.Pedido;
import com.celso.springmc.domain.dto.CategoriaDTO;
import com.celso.springmc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id) { //Busca Pedido por ID
        Pedido obj = pedidoService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)   //Paginação com parãmetros opcionais na Requisição
    public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                       @RequestParam(value = "order", defaultValue = "instante") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        Page<Pedido> listPage = pedidoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(listPage);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) { //Insere um Pedido
        obj = pedidoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
