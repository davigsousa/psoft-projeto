package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.DTO.OrientacaoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.model.SolicitacaoOrientacao;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.OrientacaoRepository;
import com.psoft.tccmatch.repository.SolicitacaoOrientacaoRepository;
import com.psoft.tccmatch.util.ErroAluno;
import com.psoft.tccmatch.util.ErroProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PropostaTCCService propostaTCCService;

    @Autowired
    private SolicitacaoOrientacaoRepository solicitacaoOrientacaoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AreaEstudoService areaEstudoService;

    @Override
    public Aluno criar(AlunoDTO dto) throws ApiException {
        Optional<Aluno> alunoExiste = alunoRepository.findByMatricula(dto.getMatricula());
        if (alunoExiste.isPresent()) {
            throw ErroAluno.erroAlunoJaExiste();
        }
        alunoExiste = alunoRepository.findByEmail(dto.getEmail());
        if (alunoExiste.isPresent()) {
            throw ErroAluno.erroAlunoJaExiste();
        }

        String senhaCriptografada = bCryptPasswordEncoder.encode(dto.getSenha());
        Aluno aluno = new Aluno(dto.getNome(), dto.getMatricula(), dto.getEmail(), senhaCriptografada, dto.getPeriodo_de_conclusao());
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno editar(AlunoDTO dto) throws ApiException {
        Aluno aluno = get(dto.getMatricula());

        aluno.setEmail(dto.getEmail());
        String senhaCriptografada = bCryptPasswordEncoder.encode(dto.getSenha());
        aluno.setSenha(senhaCriptografada);
        aluno.setMatricula(dto.getMatricula());
        aluno.setNome(dto.getNome());
        aluno.setPeriodoDeConclusao(dto.getPeriodo_de_conclusao());

        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno get(String matricula) throws ApiException {
        Optional<Aluno> aluno = alunoRepository.findByMatricula(matricula);

        if (aluno.isEmpty()) {
            throw ErroAluno.erroAlunoNaoExiste();
        }

        return aluno.get();
    }

    @Override
    public Aluno getById(Long id) throws ApiException {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);

        if(alunoOpt.isEmpty()){
            throw ErroAluno.erroAlunoNaoExiste();
        }

        return alunoOpt.get();
    }

    @Override
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }

    @Override
    public void remover(String matricula) throws ApiException {
        Aluno aluno = get(matricula);
        alunoRepository.delete(aluno);
    }

    @Override
    public SolicitacaoOrientacao solicitaOrientacao(OrientacaoDTO dto, Object user) throws ApiException {
        if (user instanceof Aluno) {
            List<PropostaTCC> propostas_disponiveis = propostaTCCService.getAllFromProf();
            PropostaTCC proposta = propostaTCCService.getById(dto.getIdThemeTCC());

            if (!propostas_disponiveis.contains(proposta)) {
                throw ErroProposta.erroPropostaNaoDisponivel();
            }

            SolicitacaoOrientacao solicitacao = new SolicitacaoOrientacao(
                    proposta,
                    (Aluno) user
            );

            solicitacaoOrientacaoRepository.save(solicitacao);
            return solicitacao;
        } else {
            throw ErroProposta.erroProposta();
        }
    }

    @Override
    public Aluno selecionarArea(String matricula, Long areaId) throws ApiException {
        Aluno aluno = this.get(matricula);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        aluno.adicionarAreaEstudo(areaEstudo);
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno desselecionarArea(String matricula, Long areaId) throws ApiException {
        Aluno aluno = this.get(matricula);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        aluno.removerAreaEstudo(areaEstudo);
        return alunoRepository.save(aluno);
    }
}
