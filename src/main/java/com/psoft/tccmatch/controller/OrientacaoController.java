package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.service.OrientacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(path = "/orientacao", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> listar(@RequestAttribute("user") Object user) {
        List<Orientacao> orientacoes = orientacaoService.getAllByProfessor((Professor) user);

        return ResponseEntity.ok(orientacoes);
    }
}
