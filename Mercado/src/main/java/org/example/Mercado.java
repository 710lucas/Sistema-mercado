package org.example;


import org.example.Exceptions.*;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.ArrayList;

public class Mercado implements Serializable{
    private ArrayList<Pagavel> funcionarios = new ArrayList<>();
    private Inventario inventario = new Inventario();
    private ArrayList<Caixa> caixas = new ArrayList<>();


    public void adicionaFuncionario(Pagavel funcionario) throws FuncionarioException {
        if(funcionarios.contains(funcionario))
            throw new FuncionarioException("Funcionario não pode ser adicionado à lista de funcionarios pois já está lá");
        funcionarios.add(funcionario);
    }

    public void adicionaFuncionario(String nome, int idade, double salario) throws PessoaInvalidaException, FuncionarioException {
        Funcionario funcionario = new Funcionario(nome, idade, salario);
        adicionaFuncionario(funcionario);
    }

    public void removeFuncionario(Pagavel funcionario){
        funcionarios.remove(funcionario);
    }

    public void removeFuncionario(String nome){
        for(Pagavel f: funcionarios){
            if(f.getNome().equals(nome))
                removeFuncionario(f);
        }
    }

    public void removeFuncionario(int posicao){
        funcionarios.remove(posicao);
    }

    public void adicionaCaixa(Caixa caixa) throws CaixaInvalidoException {
        if(caixa == null || caixas.contains(caixa))
            throw new CaixaInvalidoException();
        for(Caixa c : caixas){
            if(c.getNumero() == caixa.getNumero())
                throw new CaixaInvalidoException();
        }
        caixas.add(caixa);
    }

    public void adicionaCaixa(int numero) throws CaixaInvalidoException {
        Caixa c = new Caixa(numero, inventario);
        if(caixas.contains(c))
            throw new CaixaInvalidoException("Caixa com numero "+numero+" ja existe");
        caixas.add(c);
    }

    public Caixa getCaixa(int numero) throws CaixaInvalidoException {
        for(Caixa c : caixas){
            if(c.getNumero() == numero)
                return c;
        }
        throw new CaixaInvalidoException("Não foi possível encontrar um caixa com o numero "+numero);
    }

    public Caixa getCaixaPosicao(int posicao) throws CaixaInvalidoException {
        try {
            return caixas.get(posicao);
        }catch(IndexOutOfBoundsException e){
            throw new CaixaInvalidoException("Não há um caixa na posicao "+posicao);
        }
    }

    public Caixa getCaixa(Caixa caixa) throws CaixaInvalidoException {
        return getCaixa(caixa.getNumero());
    }

    public ArrayList<Caixa> getCaixas() {
        return caixas;
    }

    public void refillInventario(Produto produto, int quantidade) throws QuantidadeInvalidaException, ItemInvalidoException {
        if(!inventario.temProduto(produto.getNome())){
            Item item = new Item(produto, quantidade);
            inventario.adicionaProduto(item);
            return;
        }
        inventario.getItem(produto.getNome()).adiciona(quantidade);

    }

    public void refillInventario(Item item, int quantidade) throws QuantidadeInvalidaException {
        inventario.adicionaProduto(item, quantidade);
    }

    public Funcionario getFuncionario(String nome) throws FuncionarioException {
        for (Pagavel f : funcionarios){
            if(f.getNome().equals(nome) && f.getTipo().equals("funcionario"))
                return (Funcionario) f;
        }
        throw new FuncionarioException("Não existe o funcionario "+nome);
    }


    public Gerente getGerente(String nome) throws FuncionarioException {
        for (Pagavel f : funcionarios){
            if(f.getNome().equals(nome) && f.getTipo().equals("gerente"))
                return (Gerente) f;
        }
        throw new FuncionarioException("Não existe o gerente "+nome);
    }
    public Inventario getInventario(){
        return inventario;
    }

    public ArrayList<Pagavel> getFuncionarios(){
        return funcionarios;
    }


    public Mercado carregaMercado(){
        try {
            FileInputStream arquivoParaCarregar = new FileInputStream(new File("Mercado.save"));
            ObjectInputStream objetoParaCarregar = new ObjectInputStream(arquivoParaCarregar);
            return (Mercado)objetoParaCarregar.readObject();
        } catch (IOException | ClassNotFoundException e) {
            salvar();
            throw new RuntimeException(e);
        }
    }

    public void salvar(){
        try {
            FileOutputStream arquivoParaSalvar = new FileOutputStream(new File("Mercado.save"));
            ObjectOutputStream objetoParaSalvar = new ObjectOutputStream(arquivoParaSalvar);
            objetoParaSalvar.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
