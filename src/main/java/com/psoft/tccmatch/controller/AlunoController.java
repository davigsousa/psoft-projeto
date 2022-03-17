package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.service.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class AlunoController {
    @Autowired
    private AlunoServiceImpl alunoServiceImpl;

    @RequestMapping(path = "/aluno/novo", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> criar_aluno(@RequestBody AlunoDTO alunoDTO) {
        try {
            Aluno result = alunoServiceImpl.criar(alunoDTO);
            return ResponseEntity.ok(result);
        } catch (Exception err) {
            return ResponseEntity.badRequest().body(err);
        }
    }

    @RequestMapping(path = "/aluno/{matricula}", method = RequestMethod.GET)
    public ResponseEntity<?> get_aluno(@RequestParam(value = "matricula") String matricula) {
        try {
            Aluno result = alunoServiceImpl.get(matricula);
            return ResponseEntity.ok(result);
        } catch (Exception err) {
            return ResponseEntity.badRequest().body(err);
        }
    }
}