package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Laboratorio;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.repository.LaboratorioRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.util.ErroLaboratorio;
import com.psoft.tccmatch.util.ErroProfessor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Professor cria(ProfessorDTO dto) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findByEmail(dto.getEmail());

        if (professor_existe.isPresent()) {
            throw ErroProfessor.erroProfessorJaExiste();
        }

        List<Long> id_labs = dto.getLaboratorios();
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

        Professor professor = new Professor(dto.getNome(), dto.getEmail(), labs);
        professorRepository.save(professor);

        for (Laboratorio lab : labs) {
            List<Professor> current_profs = lab.getProfessores();
            current_profs.add(professor);
            lab.setProfessores(current_profs);
            laboratorioRepository.save(lab);
        }

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
        List<Laboratorio> labs = professor.laboratoriosEntities();

        for (Laboratorio lab : labs) {
            List<Professor> profs = lab.getProfessores();
            profs.remove(professor);
            lab.setProfessores(profs);
            laboratorioRepository.save(lab);
        }

        professorRepository.delete(professor);
    }

    @Override
    public void update(ProfessorDTO dto) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findById(dto.getId());

        if (professor_existe.isEmpty()) {
            throw ErroProfessor.erroProfessorNaoExiste();
        }

        Professor professor = professor_existe.get();

        List<Long> id_labs = dto.getLaboratorios();
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

        professor.setEmail(dto.getEmail());
        professor.setLaboratorios(labs);
        professor.setNome(dto.getNome());
        professorRepository.save(professor);

        for (Laboratorio lab : labs) {
            List<Professor> current_profs = lab.getProfessores();
            current_profs.add(professor);
            lab.setProfessores(current_profs);
            laboratorioRepository.save(lab);
        }
    }
}
