package barriga.domain.builders;

import barriga.domain.Usuario;

public class UsuarioBuilder {

    private UsuarioBuilder() {}

    public static UsuarioBuilder buildUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();

        builder._email = "test@test.com";
        builder._id = 1L;
        builder._nome = "Test";
        builder._senha = "test";

        return builder;
    }

    public Usuario build() {
        return new Usuario(_email, _id, _nome, _senha);
    }

    public UsuarioBuilder setEmail(String email) {
        _email = email;

        return this;
    }

    public UsuarioBuilder setId(Long id) {
        _id = id;

        return this;
    }

    public UsuarioBuilder setNome(String nome) {
        _nome = nome;

        return this;
    }

    public UsuarioBuilder setSenha(String senha) {
        _senha = senha;

        return this;
    }

    private String _email;
    private Long _id;
    private String _nome;

    private String _senha;


}
