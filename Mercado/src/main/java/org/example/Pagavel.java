package org.example;

import org.example.Exceptions.PessoaInvalidaException;

import java.io.Serializable;

public class Pagavel extends Pessoa {
    private double salario;
    private String tipo;

    public Pagavel(String nome, int idade, double salario, String tipo) throws PessoaInvalidaException {
        super(nome, idade);
        this.salario = salario;
        this.tipo = tipo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public String getTipo(){
        return tipo;
    }
}
