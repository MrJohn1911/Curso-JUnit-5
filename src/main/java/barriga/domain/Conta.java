package barriga.domain;

import barriga.domain.exception.ValidationException;

import java.util.Objects;

public class Conta {

    public Conta(Long id, String nome, Usuario usuario) {
        if(nome == null)
            throw new ValidationException("Nome e obrigatorio");
        if(usuario == null)
            throw new ValidationException("Usuario e obrigatorio");

        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    private Long id;
    private String nome;

    private Usuario usuario;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Conta conta = (Conta) object;
        return Objects.equals(id, conta.id) && Objects.equals(nome, conta.nome) && Objects.equals(usuario, conta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario);
    }
}
