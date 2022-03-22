package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.PropostaTCC;

import java.util.List;

public interface PropostaTCCService {
    PropostaTCC criar(PropostaTCCDTO dto) throws ApiException;

    PropostaTCC getById(Long id) throws ApiException;
    
    List<PropostaTCC> getAll();

    List<PropostaTCC> getAllFromProf();
}
