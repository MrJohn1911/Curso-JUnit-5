package barriga.domain;

import barriga.domain.exception.ValidationException;

import java.util.Objects;

public class Usuario {
    public Usuario(String email, Long id, String nome, String senha) {
        if(nome == null) {
            throw new ValidationException("Nome e obrigatorio!");
        }

        if(email == null) {
            throw new ValidationException("Email e obrigatorio!");
        }

        if(senha == null) {
            throw new ValidationException("Senha e obrigatoria!");
        }

        this.email = email;
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        Usuario usuario = (Usuario) object;
        return Objects.equals(email, usuario.email)
                && Objects.equals(nome, usuario.nome)
                && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, nome, senha);
    }

    private String email;
    private Long id;
    private String nome;
    private String senha;

}
