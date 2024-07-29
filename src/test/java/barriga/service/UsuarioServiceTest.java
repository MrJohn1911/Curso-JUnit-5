package barriga.service;

import barriga.domain.Usuario;
import barriga.domain.builders.UsuarioBuilder;
import barriga.domain.exception.ValidationException;
import barriga.service.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UsuarioServiceTest {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente() {
        Optional<Usuario> usuario = usuarioService.getUserByEmail("test@test.com");

        Assertions.assertTrue(usuario.isEmpty());
    }

    @Test
    public void deveRetornarUsuarioPorEmail() {
        Mockito.when(usuarioRepository.getUserByEmail("test@test.com"))
                .thenReturn(Optional.of(UsuarioBuilder.buildUsuario().build()));

        Optional<Usuario> usuario = usuarioService.getUserByEmail("test@test.com");

        Assertions.assertTrue(usuario.isPresent());

        Mockito.verify(usuarioRepository, Mockito.atLeast(1 ))
                .getUserByEmail("test@test.com");
    }

    @Test
    public void deveSalvarUsuarioComSucesso() {
        Usuario usuarioASerSalvo = UsuarioBuilder.buildUsuario().setId(null).build();

        Mockito.when(usuarioRepository.salvar(usuarioASerSalvo))
                        .thenReturn(UsuarioBuilder.buildUsuario().build());

        Usuario usuarioSalvo = usuarioService.salvar(usuarioASerSalvo);

        Assertions.assertNotNull(usuarioSalvo.getId());

        Mockito.verify(usuarioRepository).getUserByEmail(usuarioASerSalvo.getEmail());
    }

    @Test
    public void deveRejeitarUsuarioExistente() {
        Usuario userToBeSaved = UsuarioBuilder.buildUsuario()
                .setId(null)
                .build();

        Mockito.when(usuarioRepository.getUserByEmail(userToBeSaved.getEmail()))
                .thenReturn(Optional.of(UsuarioBuilder.buildUsuario().build()));

        ValidationException validationException = Assertions
                .assertThrows(ValidationException.class, () -> {
                    usuarioService.salvar(userToBeSaved);
        });

        Assertions.assertTrue(validationException.getMessage()
                .endsWith("ja esta cadastrado!"));
    }

    @InjectMocks
    private UsuarioService usuarioService;
    
    @Mock
    private UsuarioRepository usuarioRepository;

}
