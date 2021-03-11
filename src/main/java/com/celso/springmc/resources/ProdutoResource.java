package com.celso.springmc.resources;

import com.celso.springmc.domain.Categoria;
import com.celso.springmc.domain.Pedido;
import com.celso.springmc.domain.Produto;
import com.celso.springmc.domain.dto.CategoriaDTO;
import com.celso.springmc.domain.dto.ProdutoDTO;
import com.celso.springmc.repositories.ProdutoRepository;
import com.celso.springmc.resources.utils.URL;
import com.celso.springmc.services.PedidoService;
import com.celso.springmc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) { //Busca Pedido por ID
        Produto obj = produtoService.find(id);
        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(method = RequestMethod.GET)   //Paginação com parãmetros opcionais na Requisição
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "0") String nome,
                                                     @RequestParam(value = "categorias", defaultValue = "0") String categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "order", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        List<Integer> ids = URL.decodeList(categorias);
        String nomeDecod = URL.decodeParam(nome);

        Page<Produto> listPage = produtoService.search(nomeDecod, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDTO = listPage.map(ProdutoDTO::new);

        return ResponseEntity.ok(listDTO);


    }


}
