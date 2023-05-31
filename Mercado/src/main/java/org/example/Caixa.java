package org.example;

import java.util.ArrayList;

public class Caixa {

    private Inventario  inventario;
    private Pessoa pessoa;
    private double dinheiro;
    private ArrayList<Venda> vendas;
    private int numero;

    public Caixa(int numero, Inventario inventario){
        this.inventario = inventario;
        this.numero = numero;
    }

    public void vende(Item item){
        item.vende(); //adicionar exceção
    }




}
