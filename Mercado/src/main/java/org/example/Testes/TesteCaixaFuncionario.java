package org.example.Testes;


import org.example.*;
import org.example.Exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteCaixaFuncionario {

    private static Inventario inventario = new Inventario();
    private static Funcionario funcionario;

    static {
        try {
            funcionario = new Funcionario("lucas", 19, 1000);
        } catch (PessoaInvalidaException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setup() throws ItemInvalidoException, QuantidadeInvalidaException {
        inventario.adicionaItem("treloso", 10, "TRL");
    }


    @Test
    public void adiconarCarrinho() throws CaixaInvalidoException, VendaInvalidaException, PessoaInvalidaException, FuncionarioException, ItemInvalidoException, QuantidadeInvalidaException {
        CaixaFuncionario c = new CaixaFuncionario(0, inventario);
        c.login(funcionario);
        c.adicionaCarrinho(inventario.getProduto("TRL"), 1);
        Venda v = c.getVendaAtual();
        c.finalizaCompra();
        GerenciadorCaixaFuncionario g = new GerenciadorCaixaFuncionario();
        g.adicionaCaixa(c);
        System.out.println(g.getCaixaNumero(0).getDinheiro());
        System.out.println(c.getVendas().getVenda(0).getProdutosVendidos().getRelatorio());
        System.out.println(c.getDinheiro());
        System.out.println(g.getRelatorio());
        System.out.println(c.getDinheiro());

        System.out.println("=====");
        System.out.println(c.getVendas().getRelatorioCaixa(0));
    }


}
