package org.example;

import org.example.Exceptions.CaixaInvalidoException;
import org.example.Exceptions.ItemInvalidoException;
import org.example.Exceptions.PessoaInvalidaException;
import org.example.Exceptions.QuantidadeInvalidaException;

public class Gerente extends Pagavel{

    private final String tipo = "gerente";
    public Gerente(String nome, int idade, double salario) throws PessoaInvalidaException {
        super(nome, idade, salario, "gerente");
    }

    public String verInventario(Mercado mercado){

        String output = "";
        output += String.format("%-20s %-20s %-20s\n", "Nome", "Preço", "Quantidade");
        for(Item i : mercado.getInventario().getItens()){
            Produto p = i.getProduto();
            output += String.format("%-20s %-20s %-20s\n", p.getNome(), p.getPreco(), i.getQuantidade());
        }
        output+="\nValor Total do estoque: "+mercado.getInventario().getPrecoTotal();
        output+="\nQuantidade total de itens: "+mercado.getInventario().getQuantidadeTotal();
        return output;
    }

    public void adicionar(String nome, double preco, int quantidade, Mercado mercado) throws QuantidadeInvalidaException, ItemInvalidoException {
        Item item = new Item(nome, preco);
        mercado.getInventario().adicionaProduto(item, quantidade);
    }

    public void reabastecer(String nome, int quantidade, Mercado mercado) throws ItemInvalidoException, QuantidadeInvalidaException {
        Produto p = mercado.getInventario().getProduto(nome);
        mercado.refillInventario(p, quantidade);
    }

    public int adicionaCaixaFuncionario(Mercado mercado) throws CaixaInvalidoException {
        CaixaFuncionario caixa = new CaixaFuncionario(mercado.getCaixas().size(), mercado.getInventario());
        mercado.adicionaCaixa(caixa);
        return caixa.getNumero();
    }

    public int adicionaCaixaAutomatico(Mercado mercado) throws CaixaInvalidoException {
        mercado.adicionaCaixa(mercado.getCaixas().size());
        return mercado.getCaixas().size()-1;
    }


}
