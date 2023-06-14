package org.example.Testes;

import org.example.Exceptions.PessoaInvalidaException;
import org.example.Funcionario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuncionarioTest {

    public static Funcionario f;

    @BeforeEach
    public void setup() throws PessoaInvalidaException {
        f = new Funcionario("Lucas", 19, 1200);
    }

    @Test
    public void gets(){
        Assertions.assertEquals("Lucas", f.getNome());
        Assertions.assertEquals(1200, f.getSalario());
        Assertions.assertEquals(19, f.getIdade());
        Assertions.assertEquals("funcionario", f.getTipo());
        Assertions.assertFalse(f.getTrabalhando());
    }


}
