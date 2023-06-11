package org.example;

import org.example.Exceptions.*;

import java.io.Serializable;
import java.util.Date;

public class Caixa implements Serializable {


    private Pessoa pessoa;
    private double dinheiro;
    private Vendas vendas = new Vendas();
    private int numero;
    private Date horarioDeEntrada;
    private Venda vendaAtual;

    public Caixa(int numero) throws CaixaInvalidoException {
        if(numero < 0)
            throw new CaixaInvalidoException("O numero de um caixa nao pode ser menor que 0");
        this.numero = numero;
        vendaAtual = null;
    }

    public void login(String nome, int idade) throws PessoaInvalidaException {
        this.pessoa  = new Pessoa(nome, idade);
    }

    public Vendas getVendas(){
        return vendas;
    }

    public double getDinheiro(){
        return dinheiro;
    }



    public void adicionaCarrinho(Produto produto, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException, PessoaInvalidaException, CaixaInvalidoException, VendaInvalidaException {
        if(produto == null)
            throw new ItemInvalidoException();

        if(vendaAtual == null) {
            vendaAtual = new Venda(this.pessoa, this);
        }

        Item item = new Item(produto, quantidade);
        item.setQuantidade(quantidade);

        vendaAtual.adicionaItem(item);

    }

    public void removeItem(String codigo, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException {
        int quantidadeInv = vendaAtual.getProdutosVendidos().getItem(codigo).getQuantidade();
        if(quantidade <= quantidadeInv)
            vendaAtual.getProdutosVendidos().getItem(codigo).setQuantidade(quantidadeInv-quantidade);
    }

    public double finalizaCompra() throws VendaInvalidaException, PessoaInvalidaException, CaixaInvalidoException {
        double dinheiroDaCompra = vendaAtual.getProdutosVendidos().getPrecoTotal();

        vendas.adicionaVenda(vendaAtual);
        dinheiro+=vendaAtual.getProdutosVendidos().getPrecoTotal();

        vendaAtual = null;

        return dinheiroDaCompra;
    }

    public void cancelaCompra(){
        vendaAtual = null;
    }


    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setVendaAtual(Venda venda){
        this.vendaAtual = venda;
    }

    public Pessoa getFuncionario() {
        return pessoa;
    }

    public int getNumero(){
        return numero;
    }

    public Date getHorarioDeEntrada(){
        return horarioDeEntrada;
    }

    public void setHorarioDeEntrada(Date horarioDeEntrada) {
        this.horarioDeEntrada = horarioDeEntrada;
    }

    public Venda getVendaAtual() throws VendaInvalidaException {
        if(vendaAtual == null){
            throw new VendaInvalidaException("Ainda não há uma venda ocorrendo");
        }
        return vendaAtual;
    }

    public Inventario getCarrinho() throws VendaInvalidaException {
        return getVendaAtual().getProdutosVendidos();
    }
}
