package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.PropostaTCC;

import java.util.List;

public interface PropostaTCCService {
    PropostaTCC criar(PropostaTCCDTO dto, Object user) throws ApiException;

    PropostaTCC getById(Long id) throws ApiException;
    
    List<PropostaTCC> getAll(Object user) throws ApiException;

    List<PropostaTCC> getAllFromProf();

    List<PropostaTCC> getAllFromAluno();
}
