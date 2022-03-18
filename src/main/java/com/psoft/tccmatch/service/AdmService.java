package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AdmDTO;
import com.psoft.tccmatch.model.Admin;

public interface AdmService {
    Admin criar_coordenador(AdmDTO dto) throws Exception;

    Admin criar_adm(AdmDTO dto) throws Exception;
}
