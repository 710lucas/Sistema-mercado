package org.example.Exceptions;

public class ClasseInvalidaException extends Exception {
    public ClasseInvalidaException(){
        super("Houve um problema ao instanciar a classe");
    }

    public ClasseInvalidaException(String msg){
        super(msg);
    }
}
