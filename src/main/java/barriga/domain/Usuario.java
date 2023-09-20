package barriga.domain;

import barriga.domain.exception.ValidationException;

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

        _email = email;
        _id = id;
        _nome = nome;
        _senha = senha;
    }

    public String getEmail() {
        return _email;
    }

    public Long getId() {
        return _id;
    }

    public String getNome() {
        return _nome;
    }

    public String getSenha() {
        return _senha;
    }

    private String _email;
    private Long _id;
    private String _nome;
    private String _senha;

}
