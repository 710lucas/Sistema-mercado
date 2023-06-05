package org.example.Exceptions;

public class ItemInvalidoException extends Exception{
    public ItemInvalidoException(){
        super("O código informado não corresponde a nenhum item no inventário");
    }
    public ItemInvalidoException(String mensagem){
        super(mensagem);
    }
}
