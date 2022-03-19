package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @RequestMapping(path = "professor/novo", method = RequestMethod.POST)
    public ResponseEntity<?> cria(@RequestBody ProfessorDTO dto) throws ApiException {
        Professor response = professorService.cria(dto);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "professor/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") String id) throws ApiException {
        Long parsedId = Long.parseLong(id);
        Professor response = professorService.getById(parsedId);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "professor/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<Professor> response = professorService.getAll();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "professor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws ApiException {
        Long parsedId = Long.parseLong(id);
        professorService.delete(parsedId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "professor/edit", method = RequestMethod.PUT)
    public ResponseEntity<?> delete(@RequestBody ProfessorDTO dto) throws ApiException {
        professorService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
