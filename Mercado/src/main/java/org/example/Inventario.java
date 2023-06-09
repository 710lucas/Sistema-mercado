package org.example;

import org.example.Exceptions.ItemInvalidoException;
import org.example.Exceptions.QuantidadeInvalidaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Inventario implements Serializable {

    private ArrayList<Item> itens = new ArrayList<>();


    public ArrayList<Item> getItens(){
        return itens;
    }

    public Item getItem(String codigo) throws ItemInvalidoException {
        for(Item i : itens){
            if(i.getProduto().getCodigo().equals(codigo))
                return i;
        }
        throw new ItemInvalidoException("Item não foi encontrado a partir de seu codigo");
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public Produto getProduto(String codigo) throws ItemInvalidoException {
        return getItem(codigo).getProduto();
    }


    public boolean temProduto(String codigo) {
        try {
            getProduto(codigo);
            return true;
        } catch (ItemInvalidoException e) {
            return false;
        }
    }

    public boolean temProduto(Item item){
        try{
            getProduto(item.getProduto().getNome());
            return true;
        }catch (ItemInvalidoException e){
            return false;
        }
    }

    public double getPrecoTotal(){
        double total = 0;
        for(Item i : itens){
            total += i.getProduto().getPreco()* i.getQuantidade();
        }
        return total;
    }

    public int getQuantidadeTotal(){
        int total = 0;
        for(Item i : itens){
            total += i.getQuantidade();
        }
        return total;
    }

    public ArrayList<Item> ordenaItens(){
        Collections.sort(itens);
        return itens;
    }

    public void adicionaItem(Item item, int quantidade) throws QuantidadeInvalidaException {
        try{
            getItem(item.getProduto().getNome()).adiciona(quantidade);
        } catch (ItemInvalidoException e){
            item.setQuantidade(quantidade);
            itens.add(item);
        }
    }
    public void adicionaItem(Item item) throws QuantidadeInvalidaException {
        adicionaItem(item, 1);
    }

    public void adicionaItem(String nome, double preco, String codigo) throws ItemInvalidoException, QuantidadeInvalidaException {
        Item i = new Item(nome, preco, codigo);
        adicionaItem(i);
    }

    public void adicionaItem(String nome, double preco, String codigo, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException {
        Item i = new Item(nome, preco, quantidade, codigo);
        adicionaItem(i);
    }

    public void adicionaDescontoItem(Item item, int porcentagemDesconto){
        item.getProduto().setDesconto(porcentagemDesconto);
    }

    public void adicionaDescontoItem(String nome, int porcentagemDesconto) throws ItemInvalidoException {
        getProduto(nome).setDesconto(porcentagemDesconto);
    }

    public void removeDesconto(String nome) throws ItemInvalidoException {
        getProduto(nome).setDesconto(0);
    }

    public void removeDesconto(Item item){
        item.getProduto().setDesconto(0);
    }

    public void mudaPreco(String nome, double preco) throws ItemInvalidoException {
        getItem(nome).getProduto().setPreco(preco);
    }

    public void mudarCodigo(String nome, String codigo) throws ItemInvalidoException {
        if(!temProduto(codigo))
            throw new ItemInvalidoException("Não existe um item com este código");
        getProduto(codigo).setCodigo(codigo);
    }


    public String getRelatorio(){
        String out = String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n", "Nome", "Codigo", "Quantidade", "Preco", "Desconto (%)", "Valor total");
        for(Item i : itens){
            out+=String.format("%-20s %-20s %-20s %-20s %-20s %-20s\n",i.getProduto().getNome(), i.getProduto().getCodigo(), i.getQuantidade(), i.getProduto().getPreco(), i.getProduto().getDesconto(), i.calculaValorTotal());
        }
        return out;
    }



}
