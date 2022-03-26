package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ReporteDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.Reporte;
import com.psoft.tccmatch.repository.OrientacaoRepository;
import com.psoft.tccmatch.repository.ReporteRepository;
import com.psoft.tccmatch.util.ErroReporte;
import com.psoft.tccmatch.util.ErroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private OrientacaoRepository orientacaoRepository;

    @Override
    public Reporte cria(Object user, Long orientacao_id, ReporteDTO dto) throws ApiException {
        if (user instanceof Aluno) {
            user = (Aluno) user;
        } else if (user instanceof Professor) {
            user = (Professor) user;
        } else {
            throw ErroUser.erroTipoUsuario();
        }

        Optional<Orientacao> orientacao_opt = orientacaoRepository.findById(orientacao_id);

        if (orientacao_opt.isEmpty()) {
            throw ErroReporte.erroReporteJaExiste();
        }

        Orientacao orientacao = orientacao_opt.get();

        Optional<Reporte> reporte_opt = reporteRepository.findByUsuarioAndPeriodoAndProblemaAndOrientacao(
                user.toString(),
                dto.getPeriodo(),
                dto.getProblema(),
                orientacao
        );

        if (reporte_opt.isPresent()) {
            throw ErroReporte.erroReporteJaExiste();
        }

        Reporte reporte = new Reporte(dto.getPeriodo(), dto.getProblema(), orientacao, user.toString());
        return reporteRepository.save(reporte);
    }

    @Override
    public List<Reporte> list() {
        return null;
    }
}
