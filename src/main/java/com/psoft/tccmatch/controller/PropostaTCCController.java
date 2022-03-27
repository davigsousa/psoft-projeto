package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.service.PropostaTCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PropostaTCCController {
    @Autowired
    private PropostaTCCService propostaTccService;

    @RequestMapping(path = "/tccs", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    @Transactional(propagation = Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public ResponseEntity<?> criarTCC(@RequestBody PropostaTCCDTO tccDTO, @RequestAttribute("user") Object user) throws ApiException {
        PropostaTCC result = propostaTccService.criar(tccDTO, user);
        return ResponseEntity.ok(new PropostaTCCDTO.RespostaAPI(result));
    }

    @RequestMapping(path = "tcc/all", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ALUNO', 'PROFESSOR')")
    public ResponseEntity<?> getAll(@RequestAttribute("user") Object user) throws ApiException {
        List<PropostaTCC> response = propostaTccService.getAll(user);
        List<PropostaTCCDTO.RespostaAPI> result = response.stream()
                .map(PropostaTCCDTO.RespostaAPI::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "tcc/all-professor", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> getAllByProfessores() {
        List<PropostaTCC> response = propostaTccService.getAllFromProf();
        List<PropostaTCCDTO.RespostaAPI> result = response.stream()
                .map(PropostaTCCDTO.RespostaAPI::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "tcc/all-aluno", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> getAllByAlunos() {
        List<PropostaTCC> response = propostaTccService.getAllFromAluno();
        List<PropostaTCCDTO.RespostaAPI> result = response.stream()
                .map(PropostaTCCDTO.RespostaAPI::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
