package org.example;

import java.util.Date;

public class Venda {

    private Inventario produtosVendidos;
    private Date data;
    private Pessoa pessoa;
    private Caixa caixa;

    public Venda(Pessoa pessoa, Caixa caixa) {
        this.pessoa = pessoa;
        this.caixa = caixa;
    }

    public Inventario getProdutosVendidos() {
        return produtosVendidos;
    }

    public Date getData() {
        return data;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void adicionaProduto(Item item) {
        adicionaProduto(item);
    }

}
