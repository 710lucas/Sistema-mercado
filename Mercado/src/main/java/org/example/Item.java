package org.example;

import org.example.Exceptions.ClasseInvalidaException;
import org.example.Exceptions.ItemInvalidoException;
import org.example.Exceptions.QuantidadeInvalidaException;

import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable {

    private Produto produto;
    private int quantidade = 1;

    public Item(Produto p, int quantidade){
        this.produto = p;
        this.quantidade = quantidade;
    }

    public Item(Produto p){
        this.produto = p;
        this.quantidade = 1;
    }

    public Item(String nome, double preco, String codigo) throws ItemInvalidoException {
        this.produto = new Produto(preco, nome, codigo);
        this.quantidade = 1;
    }

    public Item(String nome, double preco, int quantidade, String codigo) throws ItemInvalidoException {
        this.produto = new Produto(preco, nome, codigo);
        this.quantidade = quantidade;
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void vende(){
        if(quantidade >= 1)
            quantidade-=1;
    }

    public void adiciona(int quantidade) throws QuantidadeInvalidaException {
        if(quantidade <= 0)
            throw new QuantidadeInvalidaException("É necessário adicionar uma quantidade maior que zero");
        this.quantidade+=quantidade;
    }

    public void setProduto(Produto p) throws ClasseInvalidaException {
        if(p == null)
            throw new ClasseInvalidaException("Classe produto está definida como null");
        produto = p;
    }

    public void setQuantidade(int quantidade) throws QuantidadeInvalidaException {
        if(quantidade < 0)
            throw new QuantidadeInvalidaException("Não é possível adicionar uma quantidade negativa");
        this.quantidade = quantidade;
    }

    @Override
    public int compareTo(Item o) {
        return Double.compare(this.getProduto().getPreco(), o.getProduto().getPreco());
    }

    public double calculaValorTotal() {
        return quantidade*produto.getPreco();
    }
}
