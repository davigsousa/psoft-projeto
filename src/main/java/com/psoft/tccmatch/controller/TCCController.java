package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.TCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.TCC;
import com.psoft.tccmatch.service.TCCService;
import com.psoft.tccmatch.service.TCCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class TCCController {
    @Autowired
    private TCCService tccService;

    @RequestMapping(path = "/tccs", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> criarTCC(@RequestBody TCCDTO tccDTO) throws ApiException {
        TCC result = tccService.criar(tccDTO);
        return ResponseEntity.ok(result);
    }
}
