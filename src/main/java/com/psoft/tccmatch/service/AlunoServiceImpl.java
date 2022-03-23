package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.util.ErroAluno;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
}
