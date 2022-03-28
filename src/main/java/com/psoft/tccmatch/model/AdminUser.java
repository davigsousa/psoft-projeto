package com.psoft.tccmatch.model;

import javax.persistence.*;

@Entity(name = "Admin_User")
@Table(name = "Admin_User")
public class AdminUser implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String email;
    private String senha;

    public Long getId() {
        return id;
    }

    public AdminUser() {
    }

    public AdminUser(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

    @Override
    public Tipo getTipo() {
        return Tipo.ADMIN;
    }

}
