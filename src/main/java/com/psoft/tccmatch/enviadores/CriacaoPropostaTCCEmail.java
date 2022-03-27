package com.psoft.tccmatch.enviadores;

import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriacaoPropostaTCCEmail implements EnviadorEmail {
    @Autowired
    AlunoService alunoService;

    @Override
    public void enviar(List<Object> dados) {
        List<AreaEstudo> areaEstudos = new ArrayList<>();
        for (Object dado : dados) {
            AreaEstudo area = (AreaEstudo) dado;
            areaEstudos.add(area);
        }

        List<Aluno> alunos = alunoService.getAllByAreaEstudo(areaEstudos);
        for (Aluno aluno : alunos) {
            System.out.println("NOTIFICAÇÃO: E-mail enviado para " + aluno.getEmail());
        }
    }
}
