package com.psoft.tccmatch.model;

import com.psoft.tccmatch.interfaces.Administrativo;

import java.util.Objects;

public class CargoAdministrativoAbstract implements Administrativo {
    private String nome;
    private String email;
    private String senha;

    public CargoAdministrativoAbstract(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public void login(String email, String senha) {
       boolean login = Objects.equals(this.email, email) && Objects.equals(this.senha, senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
