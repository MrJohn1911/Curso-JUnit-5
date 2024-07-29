package barriga.domain;

import barriga.domain.exception.ValidationException;

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
}
