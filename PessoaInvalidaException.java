package org.example;

public class PessoaInvalidaException extends Exception{

    public PessoaInvalidaException(){
        super("Os dados informados são inválidos");
    }

    public PessoaInvalidaException(String msg){
        super(msg);
    }

}
