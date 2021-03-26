package com.celso.springmc.resources;

import com.celso.springmc.domain.Cidade;
import com.celso.springmc.domain.Cliente;
import com.celso.springmc.domain.Estado;
import com.celso.springmc.domain.dto.CidadeDTO;
import com.celso.springmc.domain.dto.ClienteDTO;
import com.celso.springmc.domain.dto.EstadoDTO;
import com.celso.springmc.services.CidadeService;
import com.celso.springmc.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll() { //Lista Estado

        List<Estado> list = estadoService.findAll();
        List<EstadoDTO> listDTO = list.stream().map(EstadoDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);

    }

    @RequestMapping(value = "/{estadoId}/cidades",method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findAll(@PathVariable Integer estadoId) { //Lista Cidade

        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDTO = list.stream().map(CidadeDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);

    }




}
