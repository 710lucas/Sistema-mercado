package org.example;

import org.example.Exceptions.PessoaInvalidaException;

import java.util.ArrayList;

public class GerenciadorGerente {

    private ArrayList<Gerente> gerentes = new ArrayList<>();


    public void adicionarGerente(Gerente gerente) throws PessoaInvalidaException {
        if(gerente == null)
            throw new PessoaInvalidaException("Gerente informado é inválido");

        gerentes.add(gerente);
    }

    public void adicionarGerente(String nome, int idade, double salario) throws PessoaInvalidaException {
        adicionarGerente(new Gerente(nome, idade, salario));
    }

    public Gerente getGerente(String nome) throws PessoaInvalidaException {
        for(Gerente g : gerentes)
            if(g.getNome().equals(nome))
                return g;
        throw new PessoaInvalidaException("Gerente com nome "+nome+" não foi encontrado");
    }

    public Gerente getGerente(int posicao){
        return gerentes.get(posicao);
    }

    public int getPosicaoGerente(Gerente gerente) throws PessoaInvalidaException {
        if(gerente == null)
            throw new PessoaInvalidaException("Gerente informado é invalido");
        return gerentes.indexOf(gerente);
    }

    public int getPosicaoGerente(String nome) throws PessoaInvalidaException {
        return gerentes.indexOf(getGerente(nome));
    }

    public void deletaGerente(Gerente gerente){
        gerentes.remove(gerente);
    }

    public void deletaGerente(int posicao){
        gerentes.remove(posicao);
    }

    public void deletaGerente(String nome) throws PessoaInvalidaException {
        gerentes.remove(getPosicaoGerente(nome));
    }

    public boolean temGerente(Gerente g){
        return gerentes.contains(g);
    }

    public boolean temGerente(String nome) throws PessoaInvalidaException {
        return gerentes.contains(getGerente(nome));
    }

    public int getQuantidadeGerentes() {
        return gerentes.size();
    }

    public String getRelatorio(){
        String out = String.format("%-20s %-20s", "Nome", "Salario");
        for(Gerente g : gerentes)
            out+=String.format("%-20s %-20s", g.getNome(), g.getSalario());
        return out;

    }

}
