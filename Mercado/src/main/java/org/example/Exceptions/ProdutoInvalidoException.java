package org.example.Exceptions;

public class ProdutoInvalidoException extends Exception{
    public ProdutoInvalidoException(){
        super("O código informado não corresponde a nenhum item no inventário");
    }
    public ProdutoInvalidoException(String mensagem){
        super(mensagem);
    }
}
