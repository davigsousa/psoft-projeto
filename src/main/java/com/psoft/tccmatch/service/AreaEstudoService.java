package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.User;

import java.util.List;

public interface AreaEstudoService {
    AreaEstudo getByAssunto(String assunto) throws ApiException;

    AreaEstudo getById(Long id) throws ApiException;

    AreaEstudo create(AreaDeEstudoDTO dto) throws ApiException;

    List<AreaEstudo> getAll();

    void selecionar(Long areaId, User user) throws ApiException;
    void desselecionar(Long areaId, User user) throws ApiException;
}
