package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.util.ErroAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno criar(AlunoDTO dto) throws ApiException {
        Optional<Aluno> aluno_existe = alunoRepository.findByMatricula(dto.getMatricula());

        if (aluno_existe.isPresent()) {
            throw ErroAluno.erroAlunoJaExiste();
        }

        Aluno aluno = new Aluno(dto.getNome(), dto.getMatricula(), dto.getEmail(), dto.getSenha(), dto.getPeriodo_de_conclusao());
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno editar(AlunoDTO dto) throws ApiException {
        Aluno aluno = get(dto.getMatricula());

        aluno.setEmail(dto.getEmail());
        aluno.setSenha(dto.getSenha());
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
}
