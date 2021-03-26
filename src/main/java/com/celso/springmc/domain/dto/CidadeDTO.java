package com.celso.springmc.domain.dto;

import com.celso.springmc.domain.Cidade;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CidadeDTO implements Serializable {

    private Integer id;

    @NotNull(message = "Campo não pode ser vazio!")
    private String nome;

    public CidadeDTO(){}

    public CidadeDTO(Cidade obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
