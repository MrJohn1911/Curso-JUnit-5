package barriga.service.repositories;

import java.util.Optional;

import barriga.domain.Usuario;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);

    Optional<Usuario> getUserByEmail(String email);
}