package org.example;

import org.example.Exceptions.PessoaInvalidaException;

import java.io.Serializable;

public class Pagavel extends Pessoa {
    private double salario;

    public Pagavel(String nome, int idade, double salario, String tipo) throws PessoaInvalidaException {
        super(nome, idade);
        this.salario = salario;
        setTipo(tipo);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

}
