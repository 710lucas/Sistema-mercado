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

    public Item getItem(String nome) throws ItemInvalidoException {
        for(Item i : itens){
            if(i.getProduto().getNome().equals(nome))
                return i;
        }
        throw new ItemInvalidoException("Item não foi encontrado a partir de seu nome");
    }

    public Produto getProduto(String nome) throws ItemInvalidoException {
        return getItem(nome).getProduto();
    }


    public double getPrecoProduto(String nome) throws ItemInvalidoException {
        return getProduto(nome).getPreco();
    }

    public boolean temProduto(String nome) {
        try {
            getProduto(nome);
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

    public void adicionaProduto(Item item, int quantidade) throws QuantidadeInvalidaException {
        try{
            getItem(item.getProduto().getNome()).adiciona(quantidade);
        } catch (ItemInvalidoException e){
            item.setQuantidade(quantidade);
            itens.add(item);
        }
    }
    public void adicionaProduto(Item item) throws QuantidadeInvalidaException {
        adicionaProduto(item, 1);
    }

    public void adicionaProduto(String nome, double preco) throws ItemInvalidoException, QuantidadeInvalidaException {
        Item i = new Item(nome, preco);
        adicionaProduto(i);
    }

    public void adicionaProduto(String nome, double preco, int quantidade) throws ItemInvalidoException, QuantidadeInvalidaException {
        Item i = new Item(nome, preco, quantidade);
        adicionaProduto(i);
    }

    public void adicionaDescontoProduto(Item item, int porcentagemDesconto){
        item.getProduto().setDesconto(porcentagemDesconto);
    }

    public void adicionaDeescontoProduto(String nome, int porcentagemDesconto) throws ItemInvalidoException {
        getProduto(nome).setDesconto(porcentagemDesconto);
    }

    public void removeDesconto(String nome) throws ItemInvalidoException {
        getProduto(nome).setDesconto(0);
    }

    public void removeDesconto(Item item){
        item.getProduto().setDesconto(0);
    }


}
