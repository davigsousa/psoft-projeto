package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @RequestMapping(path = "/professores", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> cria(@RequestBody ProfessorDTO dto) throws ApiException {
        Professor response = professorService.cria(dto);
        return ResponseEntity.ok(new ProfessorDTO.RespostaApi(response));
    }

    @RequestMapping(path = "/professores/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> get(@PathVariable("id") String id) throws ApiException {
        Long parsedId = Long.parseLong(id);
        Professor response = professorService.getById(parsedId);
        return ResponseEntity.ok(new ProfessorDTO.RespostaApi(response));
    }

    @RequestMapping(path = "/professores", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> getAll() {
        List<Professor> response = professorService.getAll();

        List<ProfessorDTO.RespostaApi> resultList = response.stream().map(ProfessorDTO.RespostaApi::new).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

    @RequestMapping(path = "/professores/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws ApiException {
        Long parsedId = Long.parseLong(id);
        professorService.delete(parsedId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/professores/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProfessorDTO dto) throws ApiException {
        professorService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/professores/quota/{novaQuantidade}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> atualizarQuota(@PathVariable("novaQuantidade") int novaQuantidade, @RequestAttribute("user") Object user) throws ApiException {
        professorService.atualizarQuota(novaQuantidade, ((Professor) user).getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/professores/areas-estudo/{areaId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> selecionarAreaEstudo(@RequestAttribute(value = "user") Object user, @PathVariable("areaId") Long areaId) throws ApiException {
        Professor professor = (Professor) user;
        Professor result = professorService.selecionarArea(professor.getId(), areaId);
        return ResponseEntity.ok(new ProfessorDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/professores/areas-estudo/{areaId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('PROFESSOR')")
    public ResponseEntity<?> desselecionarAreaEstudo(@RequestAttribute(value = "user") Object user, @PathVariable("areaId") Long areaId) throws ApiException {
        Professor professor = (Professor) user;
        Professor result = professorService.desselecionarArea(professor.getId(), areaId);
        return ResponseEntity.ok(new ProfessorDTO.RespostaApi(result));
    }
}
