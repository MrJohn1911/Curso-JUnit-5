package barriga.infra;

import barriga.domain.Usuario;
import barriga.domain.builders.UsuarioBuilder;
import barriga.domain.exception.ValidationException;
import barriga.service.UsuarioService;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {
    private static UsuarioService usuarioService =
            new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValido() {
        Usuario user = usuarioService.salvar(UsuarioBuilder.buildUsuario().setId(null).build());
        Assertions.assertNotNull(user.getId());
    }

    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente() {
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                usuarioService.salvar(UsuarioBuilder.buildUsuario().setId(null).build())
        );

        Assertions.assertEquals("O usuario ja esta cadastrado!", exception.getMessage());
    }

}
