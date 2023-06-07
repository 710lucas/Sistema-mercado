package org.example;

import org.example.Exceptions.CaixaInvalidoException;
import org.example.Exceptions.ItemInvalidoException;
import org.example.Exceptions.PessoaInvalidaException;
import org.example.Exceptions.QuantidadeInvalidaException;

public class Gerente extends Pagavel{

    private final String tipo = "gerente";
    public Gerente(String nome, int idade, double salario) throws PessoaInvalidaException {
        super(nome, idade, salario, "gerente");
    }


}
