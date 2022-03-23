package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.service.AlunoServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AlunoController {
    @Autowired
    private AlunoServiceImpl alunoServiceImpl;

    @RequestMapping(path = "/alunos", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ResponseEntity<?> criarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoServiceImpl.criar(alunoDTO);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAlunos() {
        List<Aluno> result = alunoServiceImpl.getAll();

        List<AlunoDTO.RespostaApi> resultList = result.stream().map(AlunoDTO.RespostaApi::new).collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAluno(@PathVariable("matricula") String matricula) throws ApiException {
        Aluno result = alunoServiceImpl.get(matricula);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editarAluno(@RequestBody AlunoDTO alunoDTO) throws ApiException {
        Aluno result = alunoServiceImpl.editar(alunoDTO);
        return ResponseEntity.ok(new AlunoDTO.RespostaApi(result));
    }

    @RequestMapping(path = "/alunos/{matricula}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletarAluno(@PathVariable("matricula") String matricula) throws ApiException {
        alunoServiceImpl.remover(matricula);
        return (ResponseEntity<?>) ResponseEntity.status(204);
    }
}