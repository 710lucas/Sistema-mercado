package org.example;

import org.example.Exceptions.CaixaInvalidoException;
import org.example.Exceptions.ItemInvalidoException;
import org.example.Exceptions.PessoaInvalidaException;
import org.example.Exceptions.QuantidadeInvalidaException;

import java.io.Serializable;
import java.util.Date;

public class Venda implements Serializable {

    private Inventario produtosVendidos;
    private Date data;
    private Pessoa pessoa;
    private Caixa caixa;

    public Venda(Pessoa pessoa, Caixa caixa) throws PessoaInvalidaException, CaixaInvalidoException {
        data = new Date();
        if(pessoa == null)
            throw new PessoaInvalidaException("Pessoa informada é invalida");
        this.pessoa = pessoa;

        if(caixa == null)
            throw new CaixaInvalidoException("Caixa informado é invalido");
        this.caixa = caixa;

        data = new Date();

        produtosVendidos = new Inventario();
    }

    public Date getData(){
        return data;
    }

    public Pessoa getPessoa(){
        return pessoa;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public Inventario getProdutosVendidos(){
        return produtosVendidos;
    }

    public void adicionaProduto(Item item) throws QuantidadeInvalidaException {
        produtosVendidos.adicionaItem(item);
    }


}
