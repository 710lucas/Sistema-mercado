package org.example;

import org.example.Exceptions.ItemInvalidoException;

import java.io.Serializable;

public class Produto implements Serializable {

    private double preco;

    private String nome;
    private String codigo;

    private int desconto = 0;

    public Produto(double preco, String nome, String codigo) throws ItemInvalidoException {
        validaProduto(preco, nome);
        this.preco = preco;
        this.nome = nome;
        this.codigo = codigo;
    }

    public void validaProduto(double preco, String nome) throws ItemInvalidoException {
        if (preco <= 0){
            throw new ItemInvalidoException("O valor do produto não pode ser menor ou igual a zero");
        }
        if (nome == null){
            throw new ItemInvalidoException("O nome do produto não pode estar vazio");
        }
    }

    public void setPreco(double preco){
        this.preco = preco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDesconto(int desconto){
        this.desconto = desconto;
    }

    public double getPreco(){
        return preco-(preco*desconto/100);
    }

    public String getNome(){
        return nome;
    }

    public double getPrecoOriginal(){
        return preco;
    }

    @Override
    public String toString() {
        return "Produto: nome="+nome+", preço="+preco;
    }
}
