package barriga.infra;

import barriga.domain.Usuario;
import barriga.service.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private List<Usuario> users;
    private Long currentId;

    public UsuarioMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        salvar(new Usuario("user1@mail.com", null, "User #1", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(usuario.getEmail(), nextId(),
                usuario.getNome(), usuario.getSenha());
        users.add(newUser);

        return newUser;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Long nextId() {
        return ++currentId;
    }
}
