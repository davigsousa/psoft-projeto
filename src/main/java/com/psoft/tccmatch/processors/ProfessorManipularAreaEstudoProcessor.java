package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.service.AreaEstudoService;
import com.psoft.tccmatch.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PROFESSOR_MANIPULAR")
public class ProfessorManipularAreaEstudoProcessor implements ManipularAreaEstudoProcessor {
    @Autowired
    private AreaEstudoService areaEstudoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void selecionarArea(Long areaId, Object user) throws ApiException {
        Long id = ((Professor) user).getId();
        Professor professor = professorService.getById(id);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        professor.adicionarAreaEstudo(areaEstudo);
        professorRepository.save(professor);
    }

    @Override
    public void desselecionarArea(Long areaId, Object user) throws ApiException {
        Long id = ((Professor) user).getId();
        Professor professor = professorService.getById(id);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        professor.removerAreaEstudo(areaEstudo);
        professorRepository.save(professor);
    }
}
