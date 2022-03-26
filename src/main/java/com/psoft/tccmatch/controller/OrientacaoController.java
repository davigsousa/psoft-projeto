package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.service.OrientacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrientacaoController {

    @Autowired
    private OrientacaoService orientacaoService;

    @RequestMapping(path = "/orientacao", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody OrientacaoDTO orientacaoDTO) throws ApiException {
        Orientacao date = orientacaoService.create(orientacaoDTO);

        return ResponseEntity.ok(date);
    }
}
