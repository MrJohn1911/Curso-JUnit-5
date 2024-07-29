package barriga.domain;

import barriga.domain.builders.ContaBuilder;
import barriga.domain.builders.UsuarioBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContaTest {

    @Test
    public void deveCriarContaValida() {
        Conta conta = ContaBuilder.buildConta().build();

        Assertions.assertAll("Conta",
                () -> Assertions.assertEquals(1L, conta.getId()),
                () -> Assertions.assertEquals("Test", conta.getNome()),
                () -> Assertions.assertEquals(UsuarioBuilder.buildUsuario().build(), conta.getUsuario())
        );
    }

}
