package org.example.Exceptions;

public class FuncionarioException extends Exception {
    public FuncionarioException(){
        super("Houve um erro com o funcionario");
    }
    public FuncionarioException(String msg){
        super(msg);
    }
}
