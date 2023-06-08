package org.example.Exceptions;

public class GerenteJaExisteException extends Exception{
    public GerenteJaExisteException(){
        super("O gerente informado já existe");
    }
}
