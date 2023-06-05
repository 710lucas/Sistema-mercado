package org.example;

import org.example.Exceptions.*;

import java.util.Date;

public class CaixaFuncionario extends Caixa{

    private Inventario  inventario;
    private Funcionario funcionario;
    private double dinheiro;
    Vendas vendas;
    private int numero;
    private Date horarioDeEntrada;
    Venda vendaAtual;

    public CaixaFuncionario(int numero, Inventario inventario){
        super(numero, inventario);
    }



    public void login(Funcionario funcionario) throws FuncionarioException, PessoaInvalidaException, CaixaInvalidoException {
        if(funcionario.getTrabalhando())
            throw new FuncionarioException("Funcionario já está trabalhando");

        horarioDeEntrada = new Date();

        funcionario.setTrabalhando(true);
        this.funcionario = funcionario;

        vendaAtual = new Venda(this.funcionario, this);
    }

    public void logout(Funcionario funcionario) throws FuncionarioException {
        if(!funcionario.getTrabalhando())
            throw new FuncionarioException("Funcionario não estava trabalhando para poder exercer logout");

        funcionario.adicionaHorasTrabalhadas(horarioDeEntrada, new Date());
        funcionario.setTrabalhando(false);
        this.funcionario = funcionario;
    }


    public double finalizaCompra() throws VendaInvalidaException, PessoaInvalidaException, CaixaInvalidoException {
        double dinheiroDaCompra = vendaAtual.getProdutosVendidos().getPrecoTotal();
        vendas.adicionaVenda(vendaAtual);
        funcionario.adicionaVendas(vendaAtual);

        dinheiro+=vendaAtual.getProdutosVendidos().getPrecoTotal();
        vendaAtual = new Venda(funcionario, this);

        return dinheiroDaCompra;
    }

}
