package org.example.Exceptions;

public class VendaInvalidaException extends Exception {
    public VendaInvalidaException(){
        super("Venda invalida");
    }

    public VendaInvalidaException(String msg){
        super(msg);
    }
}
