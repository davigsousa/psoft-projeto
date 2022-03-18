package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AlunoDTO;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno criar(AlunoDTO dto) throws Exception {
        Optional<Aluno> aluno_existe = alunoRepository.findByMatricula(dto.getMatricula());

        if (aluno_existe.isPresent()) {
            throw new Exception("Aluno já existe");
        }

        Aluno aluno = new Aluno(dto.getNome(), dto.getMatricula(), dto.getEmail(), dto.getPeriodo_de_conclusao());
        return alunoRepository.save(aluno);
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

    @Override
    public List<Aluno> getAll() {
        return alunoRepository.findAll();
    }
}
