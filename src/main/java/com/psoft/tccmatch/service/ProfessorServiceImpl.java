package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.model.Laboratorio;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.repository.LaboratorioRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Override
    public Professor cria(ProfessorDTO dto) throws Exception {
        Optional<Professor> professor_existe = professorRepository.findByEmail(dto.getEmail());

        if (professor_existe.isPresent()) {
            throw new Exception("Professor já existe no sistema");
        }

        List<Long> id_labs = dto.getLaboratorios();
        List<Laboratorio> labs = new ArrayList<>();

        if (id_labs.size() > 0) {
            for (Long lab_id : id_labs) {
                Optional<Laboratorio> lab = laboratorioRepository.findById(lab_id);
                if (lab.isEmpty()) {
                    throw new Exception("Laboratório não existe");
                }
                labs.add(lab.get());
            }
        }

        Professor professor = new Professor(dto.getNome(), dto.getEmail(), labs);
        professorRepository.save(professor);
        return professor;
    }

    @Override
    public Professor get(String email) {
        return null;
    }

    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }
}
