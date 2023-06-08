package org.example;


import org.example.Exceptions.*;

import java.io.*;

public class Mercado implements Serializable{

    private GerenciadorGerente gerente = new GerenciadorGerente();
    private GerenciadorFuncionario funcionarios = new GerenciadorFuncionario();
    private Inventario inventario = new Inventario();
    private GerenciadorCaixaAutomatico caixaAutomatico = new GerenciadorCaixaAutomatico();
    private GerenciadorCaixaFuncionario caixaFuncionario = new GerenciadorCaixaFuncionario();
    private Vendas vendas = new Vendas();

    public void adicionarGerente(Gerente gerente) throws PessoaInvalidaException {
        this.gerente.adicionarGerente(gerente);
    }
    public void deletaGerente(Gerente gerente){
        this.gerente.deletaGerente(gerente);
    }
    public boolean temGerente(String nome) throws PessoaInvalidaException {
        return gerente.temGerente(nome);
    }
    public int getQuantidadeGerentes(){
        return gerente.getQuantidadeGerentes();
    }
    public void adicionarFuncionario(Funcionario funcionario) throws FuncionarioException {
        funcionarios.adicionarFuncionario(funcionario);
    }
    public Funcionario getfuncionario(String nome) throws FuncionarioException {
        return funcionarios.getFuncionario(nome);
    }
    public void deletaFuncionario(Funcionario funcionario) throws FuncionarioException {
        funcionarios.deletaFuncionario(funcionario);
    }
    public void mudarSalario(String nome, double preco) throws FuncionarioException {
        funcionarios.getFuncionario(nome).setSalario(preco);
    }
    public void adicionaItem(Item item) throws QuantidadeInvalidaException {
        inventario.adicionaItem(item);
    }
    public void adicionaItem(Item item, int quantidade) throws QuantidadeInvalidaException {
        inventario.adicionaItem(item, quantidade);
    }
    public void descontoItem(Item item, int descontoPorcentagem){
        inventario.adicionaDescontoItem(item, descontoPorcentagem);
    }
    public void mudarPreco(String nome, double preco) throws ItemInvalidoException {
        inventario.mudaPreco(nome, preco);
    }
    public double getPreco(String nome) throws ItemInvalidoException {
        return inventario.getProduto(nome).getPreco();
    }

    public String getRelatorioFuncionarios(){
        return funcionarios.getRelatorio();
    }

    public String verFuncionarios(){
        return funcionarios.verFuncionarios();
    }




}
