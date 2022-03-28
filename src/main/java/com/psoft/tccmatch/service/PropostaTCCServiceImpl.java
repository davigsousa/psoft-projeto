package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.enviadores.CriacaoPropostaTCCEmail;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.*;
import com.psoft.tccmatch.processors.PropostaTCCProcessor;
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
    private AreaEstudoService areaEstudoService;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    CriacaoPropostaTCCEmail enviadorEmail;

    private final Map<String, PropostaTCCProcessor> propostaTCCProcessors;

    public PropostaTCCServiceImpl(Map<String, PropostaTCCProcessor> propostaTCCProcessors) {
        this.propostaTCCProcessors = propostaTCCProcessors;
    }

    @Override
    public PropostaTCC criar(PropostaTCCDTO dto, User user) throws ApiException {
        Optional<PropostaTCC> tccExiste = propostaTccRepository.findByTitulo(dto.getTitulo());
        if (tccExiste.isPresent()) {
            throw ErroTCC.erroTCCJaExiste();
        }

        List<Long> idAreas = dto.getAreasEstudo();
        if (idAreas.size() == 0) {
            throw ErroTCC.erroTCCDeveTerAreaDeEstudo();
        }

        PropostaTCC propostaTcc = new PropostaTCC(
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getStatus()
        );

        for (Long areaId : idAreas) {
            AreaEstudo areaEstudo = areaEstudoService.getById(areaId);
            propostaTcc.addAreaEstudo(areaEstudo);
        }

        PropostaTCC propostaCriada = propostaTCCProcessors.get(getProcessorName(user)).criar(propostaTcc, user);
        return propostaTccRepository.saveAndFlush(propostaCriada);
    }

    @Override
    public PropostaTCC getById(Long id) throws ApiException {
        Optional<PropostaTCC> tccOpt = propostaTccRepository.findById(id);

        if(tccOpt.isEmpty()){
            throw ErroTCC.erroTCCNaoExiste();
        }

        return tccOpt.get();
    }
    
    public List<PropostaTCC> getAll(User user) throws ApiException {
        return propostaTCCProcessors.get(getProcessorName(user)).getAll(user);
    }

    @Override
    public List<PropostaTCC> getAllFromProf() {
        return propostaTccRepository.findCriadoByProfessor();
    }

    @Override
    public List<PropostaTCC> getAllFromAluno() {
        return propostaTccRepository.findCriadoByAluno();
    }

    private String getProcessorName(User user) {
        return user.getTipo().name() + "_Proposta";
    }
}
