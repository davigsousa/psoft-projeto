package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.service.AlunoServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class AlunoController {
    @Autowired
    private AlunoServiceImpl alunoServiceImpl;

    @RequestMapping(path = "/alunos", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> criar_aluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoServiceImpl.criar(alunoDTO);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.GET)
    public ResponseEntity<?> get_alunos() {
        List<Aluno> result = alunoServiceImpl.getAll();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.GET)
    public ResponseEntity<?> get_aluno(@PathVariable("matricula") String matricula) throws ApiException {
        Aluno result = alunoServiceImpl.get(matricula);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.PUT)
    public ResponseEntity<?> editarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoServiceImpl.editar(alunoDTO);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletarAluno(@PathVariable("matricula") String matricula) throws ApiException {
        alunoServiceImpl.remover(matricula);
        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}