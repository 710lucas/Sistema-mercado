package org.example;

import org.example.Exceptions.CaixaInvalidoException;

import java.util.ArrayList;

public class GerenciadorCaixaFuncionario extends GerenciadorCaixaAutomatico{


    @Override
    public void adicionaCaixa(int numero) throws CaixaInvalidoException {
        try {
            if (temCaixa(getCaixaNumero(numero)))
                throw new CaixaInvalidoException("Já existe um caixa com o mesmo numero, tente adicionar um caixa com um numero diferente");
        } catch (CaixaInvalidoException ignored){
        }
        getCaixas().add(new CaixaFuncionario(numero));
    }

    @Override
    public CaixaFuncionario getCaixaNumero(int numero) throws CaixaInvalidoException {
        for(Caixa c : getCaixas()) {
            if (c.getNumero() == numero)
                return (CaixaFuncionario) c;
        }
        throw new CaixaInvalidoException("Não há um caixa com este numero");
    }

    public CaixaFuncionario getCaixaFuncionario(int numero) throws CaixaInvalidoException {
        return getCaixaNumero(numero);
    }

}
