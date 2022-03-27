package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Professor;

import java.util.List;

public interface ProfessorService {
    Professor cria(ProfessorDTO dto) throws ApiException;

    Professor getByEmail(String email) throws ApiException;

    List<Professor> getAll();

    Professor getById(Long id) throws ApiException;

    void delete(Long id) throws ApiException;

    void update(Long id, ProfessorDTO dto) throws ApiException;

    void atualizarQuota(int novaQuantidade, Long professorId) throws ApiException;

    Professor selecionarArea(Long profId, Long areaID) throws ApiException;

    Professor desselecionarArea(Long profId, Long areaID) throws ApiException;
}
