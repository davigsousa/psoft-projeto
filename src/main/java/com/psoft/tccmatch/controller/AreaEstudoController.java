package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.service.AreaEstudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaEstudoController {
    @Autowired
    private AreaEstudoService areaEstudoService;

    @RequestMapping(path = "area-estudo/nova", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody AreaDeEstudoDTO dto) throws ApiException {
        AreaEstudo response = areaEstudoService.create(dto.getAssunto());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "area-estudo/all", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> getAll() {
        List<AreaEstudo> response = areaEstudoService.getAll();
        return ResponseEntity.ok(response);
    }
}
