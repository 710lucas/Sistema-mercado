package org.example;

import org.example.Exceptions.ItemInvalidoException;

public class Produto {

    private double preco;

    private double precoComDesconto;

    private String nome;

    private int desconto = 0;

    public Produto(double preco, String nome) throws ItemInvalidoException {
        validaProduto(preco, nome);
        this.preco = preco;
        this.nome = nome;
    }

    public void validaProduto(double preco, String nome) throws ItemInvalidoException {
        if (preco <= 0){
            throw new ItemInvalidoException("O valor do produto não pode ser menor ou igual a zero");
        }
        if (nome == null){
            throw new ItemInvalidoException("O nome do produto não pode estar vazio");
        }
    }

    public double calculaDesconto(int desconto) throws ItemInvalidoException{
        validaDesconto(desconto);
        this.desconto = desconto;
        this.precoComDesconto = preco - (preco * (desconto/100));
        return precoComDesconto;
    }

    public void validaDesconto(int desconto) throws ItemInvalidoException {
        if(desconto < 0){
            throw new ItemInvalidoException("O desconto não pode ser menor que zero");
        }
        if(desconto > 100){
            throw new ItemInvalidoException("O desconto do item não pode ultrapassar cem porcento");
        }
    }

    public void setPreco(double preco) throws ItemInvalidoException{
        validaProduto(preco, this.nome);
        this.preco = preco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDesconto(int desconto) throws ItemInvalidoException{
        validaDesconto(desconto);
        this.desconto = desconto;
    }

    public double getPrecoComDesconto() {
        return precoComDesconto;
    }

    public int getDesconto() {
        return desconto;
    }

    public double getPreco(){
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String toString(){
        return "Produto do tipo " + nome + " com o desconto de: " + desconto + "%" + " totalizando: R$" + preco;
    }
}
