package org.example;

import org.example.Exceptions.PessoaInvalidaException;
import org.example.Exceptions.VendaInvalidaException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Funcionario extends Pagavel{

    private HashMap<String, Trabalho> horasTrabalhadas;
    private Vendas vendasFeitas = new Vendas();
    private boolean trabalhando = false;
    private String tipo = "funcionario";

    public Funcionario(String nome, int idade, double salario) throws PessoaInvalidaException {
        super(nome, idade, salario, "funcionario");
        horasTrabalhadas = new HashMap<>();
    }

    public void adicionaVendas(Venda venda) throws VendaInvalidaException {
        vendasFeitas.adicionaVenda(venda);
    }

    public void adicionaHorasTrabalhadas(Date entrada, Date saida){
        Trabalho t = new Trabalho(entrada, saida);
        Calendar c = Calendar.getInstance();
        c.setTime(entrada);
        String dia = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String mes = String.valueOf(c.get(Calendar.MONTH));
        String ano = String.valueOf(c.get(Calendar.YEAR));
        String data = dia+"/"+mes+"/"+ano;
        horasTrabalhadas.put(data, t);
    }

    public int getHorasTrabalhadas(Date data){
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);
        String dataFormatada = dia+"/"+mes+"/"+ano;
        int output = 0;
        output += horasTrabalhadas.get(dataFormatada).calculaHorasTrabalhadas();
        return output;
    }

    public int getTotalHorasTrabalhadas(){
        int output = 0;
        for(Trabalho t : horasTrabalhadas.values())
            output+= t.calculaHorasTrabalhadas();
        return output;
    }

    public Vendas getVendasFeitas(){
        return vendasFeitas;
    }

    public static String formataData(Date data){
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);
        String dataFormatada = dia+"/"+mes+"/"+ano;
        return dataFormatada;
    }

    public void setTrabalhando(boolean trabalhando) {
        this.trabalhando = trabalhando;
    }

    public boolean getTrabalhando() {
        return trabalhando;
    }
}
