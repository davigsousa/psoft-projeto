package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @RequestMapping(path = "professor/novo", method = RequestMethod.POST)
    public ResponseEntity<?> cria(@RequestBody ProfessorDTO dto) throws Exception {
        Professor response = professorService.cria(dto);
        return ResponseEntity.ok(response);
    }
}
