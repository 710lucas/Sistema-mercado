package org.example;

import org.example.Exceptions.ItemInvalidoException;
import org.example.Exceptions.QuantidadeInvalidaException;

public class Item {

    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) throws QuantidadeInvalidaException {
        validaQuantidade(quantidade);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Item(Produto produto){
        this.produto = produto;
        this.quantidade = 1;
    }

    public Item(String nome, double preco) throws ItemInvalidoException {
        Produto produto = new Produto(preco, nome);
        this.produto = produto;
        this.quantidade = 1;
    }

    public Item(String nome, double preco, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException {
        Produto produto = new Produto(preco, nome);
        validaQuantidade(quantidade);
        this.produto = produto;
        this.quantidade = quantidade;
    }
    public void validaQuantidade(int quantidade) throws QuantidadeInvalidaException {
        if(quantidade < 0){
            throw new QuantidadeInvalidaException("A quantidade de itens não pode ser igual a zero");
        }
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) throws QuantidadeInvalidaException {
        validaQuantidade(quantidade);
        this.quantidade = quantidade;
    }

    public void vende() throws QuantidadeInvalidaException{
        if(quantidade-1 < 0){
            throw new QuantidadeInvalidaException("Não há produtos suficientes disponiveis");
        }
        this.quantidade = quantidade - 1;
    }

    public void vende(int quantidadeVendida) throws QuantidadeInvalidaException{
        if(quantidade-quantidadeVendida < 0){
            throw new QuantidadeInvalidaException("Não há produtos suficientes disponiveis");
        }
        this.quantidade = quantidade - quantidadeVendida;
    }

    public void adiciona(int quantidadeAdiconada){
        this.quantidade = quantidade + quantidadeAdiconada;
    }



}
