package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.repository.PropostaTCCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ALUNO_Proposta")
public class AlunoPropostaTCCProcessor implements PropostaTCCProcessor {
    @Lazy
    @Autowired
    private PropostaTCCRepository propostaTCCRepository;

    @Override
    public PropostaTCC criar(PropostaTCC propostaTCC, User user) {
        propostaTCC.setAluno((Aluno) user);

        return propostaTCC;
    }

    @Override
    public List<PropostaTCC> getAll(User user) {
        return propostaTCCRepository.findAllByAlunoId(((Aluno) user).getId());
    }
}
