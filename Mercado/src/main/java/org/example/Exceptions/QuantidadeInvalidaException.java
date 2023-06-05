package org.example.Exceptions;

public class QuantidadeInvalidaException extends Exception {
    public QuantidadeInvalidaException() {
        super("Quantidade invalida.");
    }
    public QuantidadeInvalidaException(String s) {
        super(s);
    }
}
