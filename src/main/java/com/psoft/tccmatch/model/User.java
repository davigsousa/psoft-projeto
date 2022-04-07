package com.psoft.tccmatch.model;

public interface User {
    enum Tipo {
        ADMIN,
        ALUNO,
        PROF
    }

    String getEmail();
    void setEmail(String email);

    String getSenha();
    void setSenha(String senha);

    Tipo getTipo();
}
