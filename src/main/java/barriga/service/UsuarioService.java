package barriga.service;

import barriga.domain.Usuario;
import barriga.domain.exception.ValidationException;
import barriga.service.repositories.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario) {
        usuarioRepository.getUserByEmail(usuario.getEmail()).ifPresent(user -> {
            throw new ValidationException("O usuario ja esta cadastrado!");
        });
        
        return usuarioRepository.salvar(usuario);
    }

    public Optional<Usuario> getUserByEmail(String email) {
        return usuarioRepository.getUserByEmail(email);
    }
}

