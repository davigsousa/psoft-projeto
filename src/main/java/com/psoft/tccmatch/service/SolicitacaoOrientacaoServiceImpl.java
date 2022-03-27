package com.psoft.tccmatch.service;

import com.psoft.tccmatch.enviadores.AlunoSolicitarOrientacaoEmail;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;
import com.psoft.tccmatch.util.ErroProposta;
import com.psoft.tccmatch.util.ErroSolicitacao;
import com.psoft.tccmatch.util.ErroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoOrientacaoServiceImpl implements SolicitacaoOrientacaoService {

    @Autowired
    private PropostaTCCService propostaTCCService;

    @Autowired
    private SolicitacaoOrientacaoRepository solicitacaoOrientacaoRepository;

    @Autowired
    private AlunoSolicitarOrientacaoEmail enviadorEmail;

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
            enviadorEmail.enviar(solicitacao.getProfessor().getEmail());

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

    @Override
    public List<SolicitacaoOrientacao> listar(Object user) throws ApiException {
        if (user instanceof Aluno) {
            return solicitacaoOrientacaoRepository.findAllByAlunoIdAndSolicitante(
                    ((Aluno) user).getId(),
                    "PROFESSOR"
            );
        } else if (user instanceof Professor) {
            return solicitacaoOrientacaoRepository.findAllByProfessorIdAndSolicitante(
                    ((Professor) user).getId(),
                    "ALUNO"
            );
        } else {
            throw ErroUser.erroSemSolicitacoes();
        }
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
}
