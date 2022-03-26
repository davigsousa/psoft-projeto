package com.psoft.tccmatch.controller;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Reporte;
import com.psoft.tccmatch.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReporteController {
    @Autowired
    private ReporteService reporteService;


    @RequestMapping(path = "report/{orientacaoId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ALUNO')")
    public ResponseEntity<?> reportaOrientacao(
            @RequestAttribute(value = "user") Object user,
            @PathVariable("orientacaoId") Long orientacao_id,
            @RequestBody ReporteDTO dto
    ) throws ApiException {
        Reporte reporte = reporteService.cria(user, orientacao_id, dto);
        return ResponseEntity.ok(new ReporteDTO.RespostaAPI(reporte));
    }
}
