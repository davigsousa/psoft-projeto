package com.psoft.tccmatch.service;

import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;
import com.psoft.tccmatch.util.ErroProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SolicitacaoOrientacaoServiceImpl implements SolicitacaoOrientacaoService {

    @Autowired
    private PropostaTCCService propostaTCCService;

    @Autowired
    private SolicitacaoOrientacaoRepository solicitacaoOrientacaoRepository;

    @Override
    public SolicitacaoOrientacao solicitarOrientacao(Long idProposta, Object user) throws ApiException {
        if (user instanceof Aluno) {
            PropostaTCC proposta = propostaTCCService.getById(idProposta);
            List<PropostaTCC> propostasDisponiveis = propostaTCCService.getAll(proposta.getProfessor());

            if (!propostasDisponiveis.contains(proposta)) {
                throw ErroProposta.erroPropostaNaoDisponivel();
            }

            SolicitacaoOrientacao solicitacao = new SolicitacaoOrientacao(
                    proposta,
                    (Aluno) user
            );

            solicitacaoOrientacaoRepository.save(solicitacao);
            return solicitacao;
        } else if (user instanceof Professor) {
            PropostaTCC proposta = propostaTCCService.getById(idProposta);
            List<PropostaTCC> propostasDisponiveis = propostaTCCService.getAll(proposta.getAluno());

            if (!propostasDisponiveis.contains(proposta)) {
                throw ErroProposta.erroPropostaNaoDisponivel();
            }

            SolicitacaoOrientacao solicitacao = new SolicitacaoOrientacao(proposta, (Professor) user);
            return solicitacaoOrientacaoRepository.saveAndFlush(solicitacao);
        } else {
            throw ErroProposta.erroProposta();
        }
    }
}
