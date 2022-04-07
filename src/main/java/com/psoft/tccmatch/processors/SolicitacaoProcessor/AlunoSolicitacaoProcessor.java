package com.psoft.tccmatch.processors.SolicitacaoProcessor;

import com.psoft.tccmatch.enviadores.SolicitarOrientacaoEmail;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;
import com.psoft.tccmatch.service.PropostaTCCService;
import com.psoft.tccmatch.util.ErroProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ALUNO_Solicitacao")
public class AlunoSolicitacaoProcessor implements SolicitacaoProcessor {
    @Lazy
    @Autowired
    private PropostaTCCService propostaTCCService;

    @Lazy
    @Autowired
    private SolicitarOrientacaoEmail enviadorEmail;

    @Lazy
    @Autowired
    private SolicitacaoOrientacaoRepository solicitacaoOrientacaoRepository;

    @Override
    public SolicitacaoOrientacao criar(PropostaTCC propostaTCC, User user) throws ApiException {
        List<PropostaTCC> propostasDisponiveis = propostaTCCService.getAll(propostaTCC.getProfessor());

        if (!propostasDisponiveis.contains(propostaTCC)) {
            throw ErroProposta.erroPropostaNaoDisponivel();
        }

        SolicitacaoOrientacao solicitacao = new SolicitacaoOrientacao(propostaTCC, (Aluno) user);
        enviadorEmail.enviar(solicitacao.getProfessor().getEmail());

        return solicitacaoOrientacaoRepository.saveAndFlush(solicitacao);
    }

    @Override
    public List<SolicitacaoOrientacao> listar(User user) throws ApiException {
        return solicitacaoOrientacaoRepository.findAllByAlunoIdAndSolicitante(
                ((Aluno) user).getId(),
                "PROFESSOR"
        );
    }
}
