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
import com.psoft.tccmatch.util.ErroUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public PropostaTCC criar(PropostaTCCDTO dto, Object user) throws ApiException {
        Optional<PropostaTCC> tcc_existe = propostaTccRepository.findByTitulo(dto.getTitulo());

        if (tcc_existe.isPresent()) {
            throw ErroTCC.erroTCCJaExiste();
        }

        List<Long> id_areas = dto.getAreasEstudo();

        if (id_areas.size() == 0) {
            throw ErroTCC.erroTCCDeveTerAreaDeEstudo();
        }

        PropostaTCC propostaTcc = new PropostaTCC(
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getStatus().orElse(null)
        );

        if (user instanceof Professor) {
            propostaTcc.setProfessor((Professor) user);
        } else if (user instanceof Aluno) {
            propostaTcc.setAluno((Aluno) user);
        } else {
                throw ErroProposta.erroProposta();
        }
        PropostaTCC proposta_criada = propostaTccRepository.saveAndFlush(propostaTcc);

        for (Long area_id : id_areas) {
            Optional<AreaEstudo> area = areaEstudoRepository.findById(area_id);
            if (area.isEmpty()) {
                throw ErroAreaEstudo.erroAreaNaoExiste();
            }
            AreaEstudo area_estudo = area.get();
            proposta_criada.addAreaEstudo(area_estudo);
        }

        return propostaTccRepository.saveAndFlush(proposta_criada);
    }

    @Override
    public PropostaTCC getById(Long id) throws ApiException {
        Optional<PropostaTCC> tccOpt = propostaTccRepository.findById(id);

        if(tccOpt.isEmpty()){
            throw ErroTCC.erroTCCNaoExiste();
        }

        return tccOpt.get();
    }
    
    public List<PropostaTCC> getAll(Object user) throws ApiException {
        if (user instanceof Aluno) {
            Aluno aluno = (Aluno) user;
            return propostaTccRepository.findAllByAlunoId(aluno.getId());
        } else if (user instanceof Professor) {
            Professor professor = (Professor) user;
            return propostaTccRepository.findAllByProfessorId(professor.getId());
        } else {
            throw ErroUser.erroTipoUsuario();
        }
    }

    @Override
    public List<PropostaTCC> getAllFromProf() {
        return propostaTccRepository.findCriadoByProfessor();
    }

    @Override
    public List<PropostaTCC> getAllFromAluno() {
        return propostaTccRepository.findCriadoByAluno();
    }
}
