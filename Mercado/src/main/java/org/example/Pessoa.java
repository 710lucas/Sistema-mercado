package org.example;

import org.example.Exceptions.PessoaInvalidaException;

import java.io.Serializable;

public class Pessoa implements Serializable {


    private final String NOME_INVALIDO = "Nome deve ter pelo menos uma letra";
    private final String IDADE_INVALIDA = "A pessoa deve ter uma idade maior que 0";

    private String nome;
    private int idade;
    private String tipo = "pessoa";

    public Pessoa(String nome, int idade) throws PessoaInvalidaException {

        validaPessoa(nome, idade);
        this.nome = nome;
        this.idade = idade;
    }

    private void validaPessoa(String nome, int idade) throws PessoaInvalidaException{
        if(idade <= 0)
            throw new PessoaInvalidaException(IDADE_INVALIDA);

        if(nome.length()<=0)
            throw new PessoaInvalidaException(NOME_INVALIDO);
    }

    public void setIdade(int idade) throws PessoaInvalidaException {
        if(idade <= 0)
            throw new PessoaInvalidaException(IDADE_INVALIDA);
        this.idade = idade;
    }

    public void setNome(String nome) throws PessoaInvalidaException {
        if (nome.length() == 0)
            throw new PessoaInvalidaException(NOME_INVALIDO);

        this.nome = nome;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }
}
