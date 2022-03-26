package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Orientacao;
import com.psoft.tccmatch.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    Optional<Reporte> findByUsuarioAndPeriodoAndProblemaAndOrientacao(
            String usuario,
            String periodo,
            String problema,
            Orientacao orientacao
    );
}
