package org.example.Exceptions;

public class CaixaInvalidoException extends Exception {
    public CaixaInvalidoException(){
        super("Caixa invalido");
    }
    public CaixaInvalidoException(String msg) {
        super(msg);
    }
}
