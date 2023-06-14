package org.example.Exceptions;

public class SalarioInvalidoException extends Exception{
    public SalarioInvalidoException(){
        super("Salário informado é invaldido");
    }
}
