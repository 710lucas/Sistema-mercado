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
    public void adicionarGerente(String nome, int idade, double salario) throws GerenteJaExisteException, PessoaInvalidaException {
        if (gerente.temGerente(nome))
            throw new GerenteJaExisteException();
        gerente.adicionarGerente(nome, idade, salario);
    }
    public void deletaGerente(Gerente gerente){
        this.gerente.deletaGerente(gerente);
    }
    public void deletaGerente(String nome) throws PessoaInvalidaException {
        gerente.deletaGerente(nome);
    }
    public boolean temGerente(String nome) throws PessoaInvalidaException {
        return gerente.temGerente(nome);
    }
    public void mudaSalarioGerente(String nome, double salario) throws PessoaInvalidaException {
        gerente.getGerente(nome).setSalario(salario);
    }
    public int getQuantidadeGerentes(){
        return gerente.getQuantidadeGerentes();
    }
    public String getRelatorioGerente(){
        return gerente.getRelatorio();
    }

    public void adicionarFuncionario(Funcionario funcionario) throws FuncionarioException {
        funcionarios.adicionarFuncionario(funcionario);
    }
    public void adicionarFuncionario(String nome, int idade, double salario) throws PessoaInvalidaException, FuncionarioException {
        if(funcionarios.temFuncionario(nome))
            throw new FuncionarioException("Funcionario já existe");
        funcionarios.adicionarFuncionario(nome, idade, salario);
    }
    public Funcionario getfuncionario(String nome) throws FuncionarioException {
        return funcionarios.getFuncionario(nome);
    }
    public void deletaFuncionario(Funcionario funcionario) throws FuncionarioException {
        funcionarios.deletaFuncionario(funcionario);
    }
    public void deletaFuncionario(String nome) throws FuncionarioException {
        deletaFuncionario(getfuncionario(nome));
    }
    public String getRelatorioFuncionarios(){
        return funcionarios.getRelatorio();
    }
    public String verFuncionarios(){
        return funcionarios.verFuncionarios();
    }
    public void mudarSalarioFuncionario(String nome, double preco) throws FuncionarioException {
        funcionarios.getFuncionario(nome).setSalario(preco);
    }
    public String getRelatorioCaixaAutomatico(){
        return caixaAutomatico.getRelatorio();
    }
    public void adicionarCaixaAutomatico(int numero) throws CaixaInvalidoException {
        if(numero == -1) {
            adicionarCaixaAutomatico();
            return;
        }
        caixaAutomatico.adicionaCaixa(numero,inventario);
    }
    public void adicionarCaixaAutomatico() throws CaixaInvalidoException {
        caixaAutomatico.adicionaCaixa(caixaAutomatico.getQuantidadeCaixas(), inventario);
    }
    public void removerCaixaAutomatico(int numero) throws CaixaInvalidoException {
        caixaAutomatico.removeCaixa(numero);
    }
    public void adicionarCaixaManual(int numero) throws CaixaInvalidoException {
       if(numero == -1) {
           adicionarCaixaManual();
           return;
       }
       caixaFuncionario.adicionaCaixa(numero, inventario);
    }
    public void adicionarCaixaManual() throws CaixaInvalidoException {
        caixaFuncionario.adicionaCaixa(caixaFuncionario.getQuantidadeCaixas(), inventario);
    }
    public void removerCaixaManual(int numero) throws CaixaInvalidoException {
        caixaFuncionario.removeCaixa(numero);
    }
    public String getRelatorioCaixaManual(){
        return caixaFuncionario.getRelatorio();
    }

    public String getRelatorioInventario(){
       return inventario.getRelatorio();
    }
    public void adicionarItem(String nome, double preco, int quantidade, String codigo) throws ItemInvalidoException, QuantidadeInvalidaException {
        inventario.adicionaItem(nome, preco, codigo, quantidade);
    }
    public void reabastecerItem(String codigo, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException {
        inventario.getItem(codigo).adiciona(quantidade);
    }
    public String pesquisaItemNome(String nome){
        String out = String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "Nome", "Codigo", "Quantidade", "Preco", "Desconto (%)", "Valor total");
        for(Item i : inventario.getItens()){
            if(i.getProduto().getNome().contains(nome))
                out+=String.format("%-20s %-20s %-20s %-20s %-20s\n",i.getProduto().getNome(), i.getProduto().getCodigo(), i.getQuantidade(), i.getProduto().getPreco(), i.getProduto().getDesconto(), i.calculaValorTotal());
        }
        return out;
    }
    public String pesquisaItemCodigo(String codigo){
        String out = String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "Nome", "Codigo", "Quantidade", "Preco", "Desconto (%)", "Valor total");
        for(Item i : inventario.getItens()){
            if(i.getProduto().getCodigo().contains(codigo))
                out+=String.format("%-20s %-20s %-20s %-20s %-20s\n",i.getProduto().getNome(), i.getProduto().getCodigo(), i.getQuantidade(), i.getProduto().getPreco(), i.getProduto().getDesconto(), i.calculaValorTotal());
        }
        return out;
    }
    public void descontoItem(String codigo, int desconto) throws ItemInvalidoException {
        inventario.getItem(codigo).getProduto().setDesconto(desconto);
    }

    public void removeItem(String codigo) throws ItemInvalidoException {
        inventario.getItens().remove(inventario.getItem(codigo));
    }
    public void renomeiaItem(String codigo, String novoNome) throws ItemInvalidoException {
        inventario.getItem(codigo).getProduto().setNome(novoNome);
    }
    public void mudarCodigoItem(String codigo, String novoCodigo) throws ItemInvalidoException {
        inventario.mudarCodigo(codigo, novoCodigo);
    }
    public void ordenarInventario(){
        inventario.setItens(inventario.ordenaItens());
    }

    public String getRelatorioVendas(){
        return vendas.getRelatorio();
    }

    public String getRelatorioVendasFuncionario(String nome) throws FuncionarioException {
        if (!funcionarios.temFuncionario(nome))
            throw new FuncionarioException("O funcionario escolhido não existe");
        return vendas.getRelatorioFuncionario(nome);
    }
    public String getRelatorioVendasCaixa(int numero) {
        return vendas.getRelatorioCaixa(numero);
    }

    public void descontoItem(Item item, int descontoPorcentagem){
        inventario.adicionaDescontoItem(item, descontoPorcentagem);
    }
    public void mudarPreco(String nome, double preco) throws ItemInvalidoException {
        inventario.mudaPreco(nome, preco);
    }


}
