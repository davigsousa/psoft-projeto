package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.service.PropostaTCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PropostaTCCController {
    @Autowired
    private PropostaTCCService propostaTccService;

    @RequestMapping(path = "/tccs", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> criarTCC(@RequestBody PropostaTCCDTO tccDTO) throws ApiException {
        PropostaTCC result = propostaTccService.criar(tccDTO);
        return ResponseEntity.ok(result);
    }

    @RequestMapping(path = "tcc/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<PropostaTCC> response = propostaTccService.getAll();
        return ResponseEntity.ok(response);
    }
}
