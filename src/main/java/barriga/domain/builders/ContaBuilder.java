package barriga.domain.builders;

import barriga.domain.Conta;
import barriga.domain.Usuario;

public class ContaBuilder {

    private ContaBuilder() {}

    public static ContaBuilder buildConta() {
        ContaBuilder builder = new ContaBuilder();

        builder.id = 1L;
        builder.nome = "Test";
        builder.usuario = UsuarioBuilder.buildUsuario().build();

        return builder;
    }

    public Conta build() {
        return new Conta(id, nome, usuario);
    }

    public ContaBuilder setId(Long id) {
        this.id = id;

        return this;
    }

    public ContaBuilder setNome(String nome) {
        this.nome = nome;

        return this;
    }

    public ContaBuilder setUsuario(Usuario usuario) {
        this.usuario = usuario;

        return this;
    }

    private Long id;
    private String nome;
    private Usuario usuario;

}
