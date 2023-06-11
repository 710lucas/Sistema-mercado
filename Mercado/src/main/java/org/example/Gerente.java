package org.example;

import org.example.Exceptions.PessoaInvalidaException;

public class Gerente extends Pagavel{

    private final String tipo = "gerente";
    public Gerente(String nome, int idade, double salario) throws PessoaInvalidaException {
        super(nome, idade, salario, "gerente");
    }


}
