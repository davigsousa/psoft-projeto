package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.service.AlunoService;
import com.psoft.tccmatch.service.AreaEstudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ALUNO_MANIPULAR")
public class AlunoManipularAreaEstudoProcessor implements ManipularAreaEstudoProcessor {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AreaEstudoService areaEstudoService;

    @Autowired
    private AlunoService alunoService;

    @Override
    public void selecionarArea(Long areaId, Object user) throws ApiException {
        String matricula = ((Aluno) user).getMatricula();
        Aluno aluno = alunoService.get(matricula);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        aluno.adicionarAreaEstudo(areaEstudo);
        alunoRepository.save(aluno);
    }

    @Override
    public void desselecionarArea(Long areaId, Object user) throws ApiException {
        String matricula = ((Aluno) user).getMatricula();
        Aluno aluno = alunoService.get(matricula);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        aluno.removerAreaEstudo(areaEstudo);
        alunoRepository.save(aluno);
    }
}
