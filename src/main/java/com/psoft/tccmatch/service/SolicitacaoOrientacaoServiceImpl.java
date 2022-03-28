package com.psoft.tccmatch.service;

import com.psoft.tccmatch.enviadores.SolicitarOrientacaoEmail;
import com.psoft.tccmatch.enviadores.ProfessorAceitarSolicitacaoEmail;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.processors.SolicitacaoProcessor.SolicitacaoProcessor;
import com.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;
import com.psoft.tccmatch.util.ErroProposta;
import com.psoft.tccmatch.util.ErroSolicitacao;
import com.psoft.tccmatch.util.ErroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SolicitacaoOrientacaoServiceImpl implements SolicitacaoOrientacaoService {

    @Autowired
    private PropostaTCCService propostaTCCService;

    @Autowired
    private SolicitacaoOrientacaoRepository solicitacaoOrientacaoRepository;

    @Autowired
    private SolicitarOrientacaoEmail enviadorEmail;

    @Autowired
    private ProfessorAceitarSolicitacaoEmail enviadorEmailCoordenacao;

    private final Map<String, SolicitacaoProcessor> solicitacaoProcessors;

    public SolicitacaoOrientacaoServiceImpl(Map<String, SolicitacaoProcessor> solicitacaoProcessors) {
        this.solicitacaoProcessors = solicitacaoProcessors;
    }


    @Override
    public SolicitacaoOrientacao solicitarOrientacao(Long idProposta, User user) throws ApiException {
        PropostaTCC proposta = propostaTCCService.getById(idProposta);
        return solicitacaoProcessors.get(getProcessorName(user)).criar(proposta, user);
    }

    @Override
    public List<SolicitacaoOrientacao> listar(User user) throws ApiException {
        return solicitacaoProcessors.get(getProcessorName(user)).listar(user);
    }

    @Override
    public SolicitacaoOrientacao findById(Long id) throws ApiException {
        Optional<SolicitacaoOrientacao> solicitacao = solicitacaoOrientacaoRepository.findById(id);

        if (solicitacao.isEmpty()) {
            throw ErroSolicitacao.erroSolicitacaoNaoExiste();
        }

        return solicitacao.get();
    }


    @Override
    public void aprovarSolicitacao(String mensagem, Long idSolicitacao, Object user) throws ApiException {
        SolicitacaoOrientacao solicitacao = findById(idSolicitacao);

        verificarSePodeResponder(solicitacao, user);

        solicitacao.setAprovado(true);
        solicitacao.setResposta(mensagem);

        enviadorEmailCoordenacao.enviar("admin@email.com");

        solicitacaoOrientacaoRepository.saveAndFlush(solicitacao);
    }

    @Override
    public void rejeitarSolicitacao(String mensagem, Long idSolicitacao, Object user) throws ApiException {
        SolicitacaoOrientacao solicitacao = findById(idSolicitacao);

        verificarSePodeResponder(solicitacao, user);

        solicitacao.setAprovado(false);
        solicitacao.setResposta(mensagem);

        solicitacaoOrientacaoRepository.saveAndFlush(solicitacao);
    }

    private void verificarSePodeResponder(SolicitacaoOrientacao solicitacao, Object user) throws ApiException {
        if (user instanceof Aluno) {
            Long userId = ((Aluno) user).getId();
            if (!solicitacao.getSolicitante().equals("PROFESSOR") ||
                    !solicitacao.getAluno().getId().equals(userId)) {
                throw ErroSolicitacao.erroSolicitacaoNaoExiste();
            }
        } else if (user instanceof Professor) {
            Long userId = ((Professor) user).getId();
            if (!solicitacao.getSolicitante().equals("ALUNO") ||
                    !solicitacao.getProfessor().getId().equals(userId)) {
                throw ErroSolicitacao.erroSolicitacaoNaoExiste();
            }
        } else {
            throw ErroUser.erroTipoUsuario();
        }
    }

    private String getProcessorName(User user) {
        return user.getTipo().name() + "_Solicitacao";
    }
}
