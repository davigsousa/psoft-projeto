package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.model.Professor;

import java.util.List;

public interface ProfessorService {
    Professor cria(ProfessorDTO dto) throws Exception;

    Professor get(String email);

    List<Professor> getAll();
}
