package org.example.Exceptions;

public class ItemInvalidoException extends Exception {

    public ItemInvalidoException(){
        super("Os dados informados são inválidos");
    }

    public ItemInvalidoException(String mensagem){
        super(mensagem);
    }
}
