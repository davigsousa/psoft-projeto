package com.psoft.tccmatch.processors.ReporteProcessor;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.repository.ReporteRepository;
import com.psoft.tccmatch.util.ErroReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("PROF_Reporte")
public class ProfessorReporteProcessor implements ReporteProcessor {
    @Lazy
    @Autowired
    private ProfessorRepository professorRepository;

    @Lazy
    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public Reporte criar(User user, Reporte reporte, Orientacao orientacao, ReporteDTO dto) throws ApiException {
        Professor professor = professorRepository.getOne(((Professor) user).getId());

        Optional<Reporte> reporte_opt = reporteRepository.findByProfessorAndPeriodoAndProblemaAndOrientacao(
                professor,
                dto.getPeriodo(),
                dto.getProblema(),
                orientacao
        );

        if (reporte_opt.isPresent()) {
            throw ErroReporte.erroReporteJaExiste();
        }
        reporte.setProfessor((Professor) user);

        return reporteRepository.save(reporte);
    }
}
