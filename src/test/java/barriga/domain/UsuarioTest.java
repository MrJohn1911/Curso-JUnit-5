package barriga.domain;

import barriga.domain.builders.UsuarioBuilder;
import barriga.domain.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
    @Test
    public void deveCriarUsuarioValido() {
        Usuario usuario = UsuarioBuilder.buildUsuario().build();

        Assertions.assertAll("Usuario",
                () -> Assertions.assertEquals(1L, usuario.getId()),
                () -> Assertions.assertEquals("test@test.com", usuario.getEmail()),
                () -> Assertions.assertEquals("Test", usuario.getNome()),
                () -> Assertions.assertEquals("test", usuario.getSenha())
        );
    }

    @Test
    public void deveRejeitarUsuarioSemNome() {
        ValidationException validationException =
                Assertions.assertThrows(ValidationException.class,
                        () -> UsuarioBuilder.buildUsuario().setNome(null).build()
                );

        Assertions.assertEquals("Nome e obrigatorio!", validationException.getMessage());
    }

}
