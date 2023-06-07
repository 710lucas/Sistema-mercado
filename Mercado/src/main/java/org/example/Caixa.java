package org.example;

import org.example.Exceptions.*;

import java.io.Serializable;
import java.util.Date;

public class Caixa implements Serializable {


    private Inventario  inventario;
    private Pessoa pessoa;
    private double dinheiro;
    private Vendas vendas;
    private int numero;
    private Date horarioDeEntrada;
    private Venda vendaAtual;

    public Caixa(int numero, Inventario inventario) throws CaixaInvalidoException {
        if(numero < 0)
            throw new CaixaInvalidoException("O numero de um caixa nao pode ser menor que 0");
        this.inventario = inventario;
        this.numero = numero;
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

    public Inventario getInventario() {
        return inventario;
    }


    public void vende(Item item) throws ItemInvalidoException, QuantidadeInvalidaException, PessoaInvalidaException, CaixaInvalidoException {
        if(item == null)
            throw new ItemInvalidoException();

        if(vendaAtual == null)
            vendaAtual = new Venda(this.pessoa, this);

        vendaAtual.adicionaProduto(item);

    }

    public double finalizaCompra() throws VendaInvalidaException, PessoaInvalidaException, CaixaInvalidoException {
        double dinheiroDaCompra = vendaAtual.getProdutosVendidos().getPrecoTotal();

        vendas.adicionaVenda(vendaAtual);
        dinheiro+=vendaAtual.getProdutosVendidos().getPrecoTotal();

        for(Item i : vendaAtual.getProdutosVendidos().getItens()){
            i.vende();
        }

        vendaAtual = null;
        this.pessoa = null;

        return dinheiroDaCompra;
    }

    public void cancelaCompra(){
        vendaAtual = null;
    }


    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pessoa getFuncionario() {
        return pessoa;
    }

    public int getNumero(){
        return numero;
    }

    public Venda getVendaAtual() {
        return vendaAtual;
    }
}
