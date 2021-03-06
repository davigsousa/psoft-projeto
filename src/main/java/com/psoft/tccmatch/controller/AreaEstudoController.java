package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.service.AreaEstudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreaEstudoController {
    @Autowired
    private AreaEstudoService areaEstudoService;

    @RequestMapping(path = "/areas-estudo", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody AreaDeEstudoDTO dto) throws ApiException {
        AreaEstudo response = areaEstudoService.create(dto);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/areas-estudo", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> getAll() {
        List<AreaEstudo> response = areaEstudoService.getAll();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/areas-estudo/{areaId}/interesses", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> selecionarArea(@PathVariable("areaId") Long areaId, @RequestAttribute("user") Object user) throws ApiException {
        areaEstudoService.selecionar(areaId, (User) user);
        return ResponseEntity.status(200).build();
    }

    @RequestMapping(path = "/areas-estudo/{areaId}/interesses", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> desselecionarArea(@PathVariable("areaId") Long areaId, @RequestAttribute("user") Object user) throws ApiException {
        areaEstudoService.desselecionar(areaId, (User) user);
        return ResponseEntity.status(200).build();
    }
}
