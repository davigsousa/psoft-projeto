package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.AreaEstudoRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.repository.PropostaTCCRepository;
import com.psoft.tccmatch.util.ErroAreaEstudo;
import com.psoft.tccmatch.util.ErroProposta;
import com.psoft.tccmatch.util.ErroTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaTCCServiceImpl implements PropostaTCCService {
    @Autowired
    private PropostaTCCRepository propostaTccRepository;
    @Autowired
    private AreaEstudoRepository areaEstudoRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public PropostaTCC criar(PropostaTCCDTO dto) throws ApiException {
        Optional<PropostaTCC> tcc_existe = propostaTccRepository.findByTitulo(dto.getTitulo());

        if (tcc_existe.isPresent()) {
            throw ErroTCC.erroTCCJaExiste();
        }

        List<Long> id_areas = dto.getAreasEstudo();
        List<AreaEstudo> areas = new ArrayList<>();

        if (id_areas.size() == 0) {
            throw ErroTCC.erroTCCDeveTerAreaDeEstudo();
        }


        for (Long area_id : id_areas) {
            Optional<AreaEstudo> area = areaEstudoRepository.findById(area_id);
            if (area.isEmpty()) {
                throw ErroAreaEstudo.erroAreaNaoExiste();
            }
            areas.add(area.get());
        }

//        Alterar para buscar pelo id vindo do token de login
        Optional<Professor> professor_opt = professorRepository.findById(Long.parseLong("1"));

        if (professor_opt.isPresent()) {
            PropostaTCC propostaTcc = new PropostaTCC(
                    dto.getTitulo(),
                    dto.getDescricao(),
                    dto.getStatus(),
                    areas,
                    professor_opt.get()
            );
            return propostaTccRepository.save(propostaTcc);
        } else {
            Optional<Aluno> aluno_opt = alunoRepository.findById(Long.parseLong("1"));
            if (aluno_opt.isPresent()) {
                PropostaTCC propostaTcc = new PropostaTCC(
                        dto.getTitulo(),
                        dto.getDescricao(),
                        dto.getStatus(),
                        areas,
                        aluno_opt.get()
                );
                return propostaTccRepository.save(propostaTcc);
            } else {
                throw ErroProposta.erroProposta();
            }
        }
    }

    @Override
    public PropostaTCC getById(Long id) throws ApiException {
        Optional<PropostaTCC> tccOpt = propostaTccRepository.findById(id);

        if(tccOpt.isEmpty()){
            throw ErroTCC.erroTCCNaoExiste();
        }

        return tccOpt.get();
    }
    
    public List<PropostaTCC> getAll() { return propostaTccRepository.findAll(); }

    @Override
    public List<PropostaTCC> getAllFromProf() {
        return propostaTccRepository.findCriadoByProfessor();
    }
}
