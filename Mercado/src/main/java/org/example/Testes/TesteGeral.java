package org.example.Testes;

import org.example.*;
import org.example.Exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteGeral {

    @Test
    public void testeProduto() throws ItemInvalidoException {
        Produto p = new Produto(10, "Teste", "T");
        Assertions.assertEquals(10, p.getPreco());
        Assertions.assertEquals(10, p.getPrecoOriginal());
        Assertions.assertEquals("Teste", p.getNome());
        Assertions.assertEquals("T", p.getCodigo());

        p.setDesconto(1);
        Assertions.assertEquals(10*0.99, p.getPreco());
        Assertions.assertEquals(10, p.getPrecoOriginal());
    }

    @Test
    public void testeItem() throws ItemInvalidoException, QuantidadeInvalidaException, ClasseInvalidaException {
        Item i = new Item("Teste", 10, 1, "T");
        Assertions.assertEquals("Teste", i.getProduto().getNome());
        Assertions.assertEquals(1, i.getQuantidade());
        i.adiciona(1);
        Assertions.assertEquals(2, i.getQuantidade());
        i.vende();
        Assertions.assertEquals(1, i.getQuantidade());
        i.adiciona(9);
        Assertions.assertEquals(10*10, i.calculaValorTotal());

        Produto p = new Produto(5, "Teste2", "T");
        i.setProduto(p);
        Assertions.assertEquals("Teste2", i.getProduto().getNome());
        Assertions.assertEquals(50, i.calculaValorTotal());

    }

    @Test
    public void testeInventario() throws ItemInvalidoException, QuantidadeInvalidaException {
        Inventario inventario = new Inventario();
        Item i = new Item("Teste", 10, 1, "T");
        Produto p = new Produto(5, "Teste2", "T");
        inventario.adicionaItem(i);

        Assertions.assertEquals(i, inventario.getItem("T"));
        inventario.adicionaItem(p.getNome(), p.getPreco(), p.getCodigo());
        Assertions.assertEquals(i, inventario.getItem("T"));
        Assertions.assertEquals(2, i.getQuantidade());

        Item i2 = new Item("Teste2", 5, 1, "T2");

        inventario.adicionaItem(i2);

        Assertions.assertEquals(1, inventario.getPosicao(i2));

        inventario.ordenaItens();
        Assertions.assertEquals(0, inventario.getPosicao(i2));
    }

    @Test
    public void testePessoa() throws PessoaInvalidaException {
        Pessoa p = new Pessoa("lucas", 19);
        Assertions.assertEquals("lucas", p.getNome());
        Assertions.assertEquals("pessoa", p.getTipo());
        Assertions.assertEquals(19, p.getIdade());
        p.setNome("Teste");
        p.setIdade(23);
        Assertions.assertEquals("Teste", p.getNome());
        Assertions.assertEquals(23, p.getIdade());
    }

    @Test
    public void testePagavel() throws PessoaInvalidaException {
        Pagavel p = new Pagavel("lucas", 19, 800, "pagavel");
       Assertions.assertEquals(800, p.getSalario());
    }

    @Test
    public void testeVenda() throws PessoaInvalidaException, CaixaInvalidoException, ItemInvalidoException, QuantidadeInvalidaException {
        Pessoa p = new Pessoa("lucas", 19);
        Caixa c = new Caixa(0);
        Venda v = new Venda(p, c);

        Assertions.assertEquals(p, v.getPessoa());
        Assertions.assertEquals(0 , v.getProdutosVendidos().getQuantidadeTotal());
        v.adicionaItem(new Item("Teste", 10, 1, "T"));
        Assertions.assertEquals("Teste", v.getProdutosVendidos().getProduto("T").getNome());

    }



}
