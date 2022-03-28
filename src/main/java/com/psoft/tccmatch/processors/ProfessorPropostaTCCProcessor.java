package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.enviadores.CriacaoPropostaTCCEmail;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.PropostaTCCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("PROF_Proposta")
public class ProfessorPropostaTCCProcessor implements PropostaTCCProcessor {
    @Lazy
    @Autowired
    private AlunoRepository alunoRepository;

    @Lazy
    @Autowired
    CriacaoPropostaTCCEmail enviadorEmail;

    @Lazy
    @Autowired
    private PropostaTCCRepository propostaTCCRepository;

    @Override
    public PropostaTCC criar(PropostaTCC propostaTCC, User user) {
        propostaTCC.setProfessor((Professor) user);
        notificarTodosAlunos(propostaTCC);

        return propostaTCC;
    }

    @Override
    public List<PropostaTCC> getAll(User user) {
        return propostaTCCRepository.findAllByProfessorId(((Professor) user).getId());
    }

    private void notificarTodosAlunos(PropostaTCC propostaTCC) {
        List<Aluno> alunos = alunoRepository.findAllByAreasEstudoIn(propostaTCC.getAreasEstudo());
        for (Aluno aluno : alunos) {
            enviadorEmail.enviar(aluno.getEmail());
        }
    }
}
