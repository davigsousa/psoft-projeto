package com.psoft.tccmatch.DTO;

import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;

import java.util.List;


public class AlunoDTO {
    private String nome;
    private String email;
    private String matricula;
    private String senha;
    private String periodo_de_conclusao;


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPeriodo_de_conclusao() {
        return periodo_de_conclusao;
    }

    public String getSenha() {
        return senha;
    }

    public static class RespostaApi {
        public Long id;
        public String nome;
        public String email;
        public String matricula;
        public String periodo_de_conclusao;
        public List<AreaEstudo> areasEstudo;

        public RespostaApi(Aluno aluno) {
            this.id = aluno.getId();
            this.nome = aluno.getNome();
            this.email = aluno.getEmail();
            this.matricula = aluno.getMatricula();
            this.periodo_de_conclusao = aluno.getPeriodoDeConclusao();
            this.areasEstudo = aluno.getAreasEstudo();
        }
    }
}
