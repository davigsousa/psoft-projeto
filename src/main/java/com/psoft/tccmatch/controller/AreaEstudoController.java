package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.service.AreaEstudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaEstudoController {
    @Autowired
    private AreaEstudoService areaEstudoService;

    @RequestMapping(path = "area-estudo/nova", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody String label) throws ApiException {
        AreaEstudo response = areaEstudoService.create(label);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "area-estudo/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<AreaEstudo> response = areaEstudoService.getAll();
        return ResponseEntity.ok(response);
    }
}
