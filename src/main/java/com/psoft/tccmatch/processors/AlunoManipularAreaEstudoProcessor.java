package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("ALUNO")
public class AlunoManipularAreaEstudoProcessor implements ManipularAreaEstudoProcessor {
    @Lazy
    @Autowired
    private AlunoRepository alunoRepository;

    @Lazy
    @Autowired
    private AlunoService alunoService;

    @Override
    public void selecionarArea(AreaEstudo area, User user) throws ApiException {
        String matricula = ((Aluno) user).getMatricula();
        Aluno aluno = alunoService.get(matricula);

        aluno.adicionarAreaEstudo(area);
        alunoRepository.save(aluno);
    }

    @Override
    public void desselecionarArea(AreaEstudo area, User user) throws ApiException {
        String matricula = ((Aluno) user).getMatricula();
        Aluno aluno = alunoService.get(matricula);

        aluno.removerAreaEstudo(area);
        alunoRepository.save(aluno);
    }
}
