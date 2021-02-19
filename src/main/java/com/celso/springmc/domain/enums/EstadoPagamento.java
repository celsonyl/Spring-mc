package com.celso.springmc.domain.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2,"Quitado"),
    CANCELADO(3,"Cancelado");

    private int id;
    private String nome;

    private EstadoPagamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(EstadoPagamento x : EstadoPagamento.values()) {
            if (cod.equals(x)) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido: "+ cod);
    }
}
