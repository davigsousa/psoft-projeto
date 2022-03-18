package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AdmDTO;
import com.psoft.tccmatch.model.AdminUser;

public interface AdmService {
    AdminUser criar_coordenador(AdmDTO dto) throws Exception;

    AdminUser criar_adm(AdmDTO dto) throws Exception;
}
