package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.repository.AlunoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {
    @Autowired
    private AlunoRepositoryImpl alunoRepository;

    @Override
    public Aluno criar(AlunoDTO dto) throws Exception {
        Optional<Aluno> aluno = alunoRepository.findByMatricula(dto.getMatricula());

        if (aluno.isPresent()) {
            throw new Exception("Aluno já existe");
        }

        return new Aluno(dto.getNome(), dto.getEmail(), dto.getMatricula(), dto.getPeriodo_de_conclusao());
    }

    @Override
    public void editar(AlunoDTO dto) {
        return;
    }

    @Override
    public Aluno get(String matricula) throws Exception {
        Optional<Aluno> aluno = alunoRepository.findByMatricula(matricula);

        if (aluno.isEmpty()) {
            throw new Exception("Aluno não existe");
        }

        return aluno.get();
    }
}
