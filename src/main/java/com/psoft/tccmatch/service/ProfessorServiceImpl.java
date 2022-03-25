package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.ProfessorDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Laboratorio;
import com.psoft.tccmatch.model.Professor;
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

    @Override
    public Professor cria(ProfessorDTO dto) throws ApiException {
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
    public void update(ProfessorDTO dto) throws ApiException {
        Optional<Professor> professor_existe = professorRepository.findById(dto.getId());

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
        int novoMaxOrientandos = dto.getMaxOrientandos().orElse(professor.getMaxOrientandos());
        professor.setMaxOrientandos(novoMaxOrientandos);
        professorRepository.save(professor);

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

    @Override
    public Professor selecionarArea(Long profID, Long areaId) throws ApiException {
        Professor professor = this.getById(profID);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        professor.adicionarAreaEstudo(areaEstudo);
        return professorRepository.save(professor);
    }

    @Override
    public Professor desselecionarArea(Long profID, Long areaId) throws ApiException {
        Professor professor = this.getById(profID);
        AreaEstudo areaEstudo = areaEstudoService.getById(areaId);

        professor.removerAreaEstudo(areaEstudo);
        return professorRepository.save(professor);
    }
}
