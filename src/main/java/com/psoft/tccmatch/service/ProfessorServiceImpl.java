package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.CriacaoProfessorDTO;
import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.repository.LaboratorioRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.util.ErroLaboratorio;
import com.psoft.tccmatch.util.ErroProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AreaEstudoService areaEstudoService;
    @Autowired
    private OrientacaoService orientacaoService;

    @Override
    public Professor cria(CriacaoProfessorDTO dto) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findByEmail(dto.getEmail());

        if (professor_existe.isPresent()) {
            throw ErroProfessor.erroProfessorJaExiste();
        }

        List<Laboratorio> labs = geraLabs(dto.getLaboratorios());

        String senhaCriptografada = bCryptPasswordEncoder.encode(dto.getSenha());
        Professor professor = new Professor(dto.getNome(), dto.getEmail(), labs, senhaCriptografada);
        professorRepository.save(professor);

        return professor;
    }

    @Override
    public Professor getByEmail(String email) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findByEmail(email);

        if (professor_existe.isEmpty()) {
            throw ErroProfessor.erroProfessorNaoExiste();
        }

        return professor_existe.get();
    }

    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor getById(Long id) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findById(id);

        if (professor_existe.isEmpty()) {
            throw ErroProfessor.erroProfessorNaoExiste();
        }

        return professor_existe.get();
    }

    @Override
    public void delete(Long id) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findById(id);

        if (professor_existe.isEmpty()) {
            throw ErroProfessor.erroProfessorNaoExiste();
        }

        Professor professor = professor_existe.get();

        professorRepository.delete(professor);
    }

    @Override
    public Professor update(Long id, ProfessorDTO dto) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findById(id);

        if (professor_existe.isEmpty()) {
            throw ErroProfessor.erroProfessorNaoExiste();
        }

        Professor professor = professor_existe.get();

        List<Laboratorio> labs = geraLabs(dto.getLaboratorios());

        professor.setEmail(dto.getEmail());
        professor.setLaboratorios(labs);
        professor.setNome(dto.getNome());
        String senhaCriptografada = bCryptPasswordEncoder.encode(dto.getSenha());
        professor.setSenha(senhaCriptografada);
        int novoMaxOrientandos = dto.getMaxOrientandos();
        professor.setMaxOrientandos(novoMaxOrientandos);

        return professorRepository.save(professor);
    }

    @Override
    public void atualizarQuota(int novaQuantidade, Long professorId) throws ApiException {
        Professor professor = getById(professorId);

        boolean disponibilidade;
        if (novaQuantidade > professor.getMaxOrientandos()) {
            disponibilidade = true;
        } else {
            List<Orientacao> orientacoes = orientacaoService.getAllActive(professor);
            disponibilidade = orientacoes.size() < novaQuantidade;
        }

        professor.setMaxOrientandos(novaQuantidade);
        professor.setDisponivel(disponibilidade);

        professorRepository.saveAndFlush(professor);
    }

    private List<Laboratorio> geraLabs(List<Long> id_labs) throws ApiException {
        List<Laboratorio> labs = new ArrayList<>();

        if (id_labs.size() > 0) {
            for (Long lab_id : id_labs) {
                Optional<Laboratorio> lab = laboratorioRepository.findById(lab_id);
                if (lab.isEmpty()) {
                    throw ErroLaboratorio.erroLabNaoExiste();
                }
                labs.add(lab.get());
            }
        }

        return labs;
    }

}
