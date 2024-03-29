package org.example;

import org.example.Exceptions.FuncionarioException;
import org.example.Exceptions.PessoaInvalidaException;

import java.io.Serializable;
import java.util.ArrayList;

public class GerenciadorFuncionario implements Serializable {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public void adicionarFuncionario(Funcionario f) throws FuncionarioException {
        if(f == null)
            throw new FuncionarioException("Funcionario informado para adicionar é inválido");
        funcionarios.add(f);
    }

    public void adicionarFuncionario(String nome, int idade, double salario) throws PessoaInvalidaException {
        Funcionario f = new Funcionario(nome, idade, salario);
        funcionarios.add(f);
    }

    public Funcionario getFuncionario(int posicao){
        return funcionarios.get(posicao);
    }

    public Funcionario getFuncionario(String nome) throws FuncionarioException {
        for(Funcionario f : funcionarios){
            if(f.getNome().equals(nome))
                return f;
        }
        throw new FuncionarioException("Não foi possível achar o funcionário com nome "+nome);
    }

    public boolean temFuncionario(Funcionario f){
        return funcionarios.contains(f);
    }

    public boolean temFuncionario(String nome){
        try {
            return funcionarios.contains(getFuncionario(nome));
        } catch (FuncionarioException e) {
            return false;
        }
    }

    public void deletaFuncionario(String nome) throws FuncionarioException {
        if(!temFuncionario(nome))
            throw new FuncionarioException("Não existe o funcionário que voce deseja apagar");
        funcionarios.remove(getFuncionario(nome));
    }

    public void deletaFuncionario(int posicao){
        funcionarios.remove(posicao);
    }

    public void deletaFuncionario(Funcionario f) throws FuncionarioException {
        if(!temFuncionario(f))
            throw new FuncionarioException("Não existe o funcionario que voce deseja apagar");
        funcionarios.remove(f);
    }

    public int getFuncionarioPosicao(String nome) throws FuncionarioException {
        return funcionarios.indexOf(getFuncionario(nome));
    }

    public int getFuncinarioPosicao(Funcionario f) throws FuncionarioException {
        if(!temFuncionario(f))
            throw new FuncionarioException("Não existe o funcionario que voce deseja apagar");
        return funcionarios.indexOf(f);
    }

    public String getRelatorio(){
        String out = String.format("%-20s %-40s %-20s %-20s\n", "Nome", "Quantidade de vendas feitas", "Total arrecadado", "Salario");
        for(Funcionario f : funcionarios){
            out+= String.format("%-20s %-40s %-20s %-20s\n", f.getNome(), f.getVendasFeitas().getTamanho(), f.getVendasFeitas().calculaTodasVendas(), f.getSalario());
        }
        return out;
    }

    public String verFuncionarios(){
        String out= "";
        for(Funcionario f : funcionarios){
            out+=f.getNome()+"\n";
        }
        return out;
    }

}
