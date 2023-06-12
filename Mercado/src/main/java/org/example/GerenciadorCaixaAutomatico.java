package org.example;

import org.example.Exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;

public class GerenciadorCaixaAutomatico implements Serializable {
    private ArrayList<Caixa> caixas = new ArrayList<>();

    public void adicionaCaixa(int numero) throws CaixaInvalidoException {
        if(temCaixa(getCaixaNumero(numero)))
            throw new CaixaInvalidoException("Já existe um caixa com o mesmo numero, tente adicionar um caixa com um numero diferente");
        caixas.add(new Caixa(numero));
    }

    public void adicionaCaixa(Caixa caixa) throws CaixaInvalidoException {
        if(caixa == null)
            throw new CaixaInvalidoException();
        caixas.add(caixa);
    }

    public boolean temCaixa(Caixa caixa){
        return caixas.contains(caixa);
    }

    public Caixa getCaixa(int posicao){
        return caixas.get(posicao);
    }

    public Caixa getCaixaNumero(int numero) throws CaixaInvalidoException {
        return getCaixa(getPosicaoCaixa(numero));
    }

    public int getPosicaoCaixa(Caixa caixa) throws CaixaInvalidoException {
        if(!temCaixa(caixa))
            throw new CaixaInvalidoException();
        return caixas.indexOf(caixa);
    }

    public int getPosicaoCaixa(int numero) throws CaixaInvalidoException {
        for(Caixa c : caixas){
            if(c.getNumero() == numero)
                return caixas.indexOf(c);
        }
        throw new CaixaInvalidoException("Não foi possível encontrar o caixa com o numero "+numero);
    }

    public void removeCaixa(Caixa caixa) throws CaixaInvalidoException {
        if(caixa == null)
            throw new CaixaInvalidoException();
        caixas.remove(caixa);
    }

    public void removeCaixa(int numero) throws CaixaInvalidoException {
        caixas.remove(getCaixaNumero(numero));
    }

    public int getQuantidadeCaixas(){
        return caixas.size();
    }

    public ArrayList<Caixa> getCaixas() {
        return caixas;
    }

    public String getRelatorio(){
        String out = String.format("%-20s %-40s %-20s\n", "Numero de caixa", "Quantidade de vendas", "Rendimento total");
        for(Caixa c : caixas){
            out+=String.format("%-20s %-40s %-20s\n", c.getNumero(), c.getVendas().getTamanho(), c.getVendas().calculaTodasVendas());
        }
        return out;
    }



}
