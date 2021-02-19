package com.celso.springmc.domain;

import com.celso.springmc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento{

    private int numeroDeParcelas;

    public PagamentoComCartao(){}

    public PagamentoComCartao(int id, EstadoPagamento estadoPagamento, Pedido pedido, int numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public int getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(int numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
