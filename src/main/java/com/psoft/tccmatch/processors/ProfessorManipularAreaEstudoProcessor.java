package com.psoft.tccmatch.processors;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.service.AreaEstudoService;
import com.psoft.tccmatch.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("PROF")
public class ProfessorManipularAreaEstudoProcessor implements ManipularAreaEstudoProcessor {
    @Lazy
    @Autowired
    private ProfessorService professorService;

    @Lazy
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void selecionarArea(AreaEstudo area, User user) throws ApiException {
        Long id = ((Professor) user).getId();
        Professor professor = professorService.getById(id);

        professor.adicionarAreaEstudo(area);
        professorRepository.save(professor);
    }

    @Override
    public void desselecionarArea(AreaEstudo area, User user) throws ApiException {
        Long id = ((Professor) user).getId();
        Professor professor = professorService.getById(id);

        professor.removerAreaEstudo(area);
        professorRepository.save(professor);
    }
}
