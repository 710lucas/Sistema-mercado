package org.example.Testes;

import org.example.Exceptions.PessoaInvalidaException;
import org.example.Gerente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenteTeste {

    Gerente g;

    @BeforeEach
    public void setup() throws PessoaInvalidaException {
        g = new Gerente("Lucas", 19, 1200);
    }

    @Test
    public void gets(){
        Assertions.assertEquals("Lucas", g.getNome());
        Assertions.assertEquals(1200, g.getSalario());
        Assertions.assertEquals(19, g.getIdade());
        Assertions.assertEquals("gerente", g.getTipo());
    }
}
