package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoOrientacaoRepository extends JpaRepository<SolicitacaoOrientacao, Long> {
    List<SolicitacaoOrientacao> findAllByProfessorIdAndSolicitante(Long id, String solicitante);
    List<SolicitacaoOrientacao> findAllByAlunoIdAndSolicitante(Long id, String solicitante);
}
