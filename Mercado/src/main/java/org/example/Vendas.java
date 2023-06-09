package org.example;

import org.example.Exceptions.VendaInvalidaException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Vendas implements Serializable {

    private ArrayList<Venda> vendas = new ArrayList<>();


    public void adicionaVenda(Venda venda) throws VendaInvalidaException {
        if(venda == null)
            throw new VendaInvalidaException();
        vendas.add(venda);
    }

    public double calculaTodasVendas(){
        double total = 0;
        for(Venda venda : vendas){
            total += venda.getProdutosVendidos().getPrecoTotal();
        }
        return total;
    }

    public Venda getVenda(int posicao){
        return vendas.get(posicao);
    }

    public int getPosicao(Venda venda){
        return vendas.indexOf(venda);
    }

    public ArrayList<Venda> getVendasDia(Date data){
        ArrayList<Venda> inicio = getVendasAno(data);

        Calendar c = Calendar.getInstance();
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        ArrayList<Venda> output = new ArrayList<>();

        for(Venda venda : inicio){
            c.setTime(venda.getData());
            int dia2 = c.get(Calendar.DAY_OF_MONTH);
            if(dia == dia2)
                output.add(venda);
        }

        return output;
    }

    public ArrayList<Venda> getVendasMes(Date data){
        ArrayList<Venda> inicio = getVendasAno(data);

        Calendar c = Calendar.getInstance();
        c.setTime(data);
        int mes = c.get(Calendar.MONTH);

        ArrayList<Venda> output = new ArrayList<>();

        for(Venda venda : inicio){
            c.setTime(venda.getData());
            int mes2 = c.get(Calendar.MONTH);
            if(mes == mes2)
                output.add(venda);
        }

        return output;

    }

    public ArrayList<Venda> getVendasAno(Date data){
        Calendar c = Calendar.getInstance();

        c.setTime(data);
        int ano = c.get(Calendar.YEAR);
        ArrayList<Venda> output = new ArrayList<>();

        for(Venda venda: vendas){
            c.setTime(venda.getData());
            int ano2 = c.get(Calendar.YEAR);
            if(ano == ano2)
                output.add(venda);
        }
        return output;
    }

    public ArrayList<Venda> getVendas(){
        return vendas;
    }

    public int getTamanho(){
        return vendas.size();
    }

    public String getRelatorio(){
        String out = String.format("%-20s %-20s %-20s\n", "Data", "Funcionario/Pessoa", "Numero Caixa");
        for (Venda v : vendas){
            Calendar c = Calendar.getInstance();
            c.setTime(v.getData());
            String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
            String mes = String.valueOf(c.get(Calendar.MONTH));
            String ano = String.valueOf(c.get(Calendar.YEAR));
            out+=String.format("%-20s %-20s %-20s\n", dia+"/"+mes+"/ano", v.getPessoa().getTipo()+":"+v.getPessoa().getNome(), v.getCaixa().getNumero());
        }
        return out;
    }

    public String getRelatorioFuncionario(String nome){
        String out = String.format("%-20s %-20s %-20s\n", "Data", "Funcionario/Pessoa", "Numero Caixa");
        for (Venda v : vendas){
            if(v.getPessoa().getNome().equals(nome)) {
                Calendar c = Calendar.getInstance();
                c.setTime(v.getData());
                String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                String mes = String.valueOf(c.get(Calendar.MONTH));
                String ano = String.valueOf(c.get(Calendar.YEAR));
                out += String.format("%-20s %-20s %-20s\n", dia + "/" + mes + "/ano", v.getPessoa().getTipo() + ":" + v.getPessoa().getNome(), v.getCaixa().getNumero());
            }
        }
        return out;
    }
    public String getRelatorioCaixa(int numero){
        String out = String.format("%-20s %-20s %-20s\n", "Data", "Funcionario/Pessoa", "Numero Caixa");
        for (Venda v : vendas){
            if(v.getCaixa().getNumero() == numero) {
                Calendar c = Calendar.getInstance();
                c.setTime(v.getData());
                String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                String mes = String.valueOf(c.get(Calendar.MONTH));
                String ano = String.valueOf(c.get(Calendar.YEAR));
                out += String.format("%-20s %-20s %-20s\n", dia + "/" + mes + "/ano", v.getPessoa().getTipo() + ":" + v.getPessoa().getNome(), v.getCaixa().getNumero());
            }
        }
        return out;
    }
}
