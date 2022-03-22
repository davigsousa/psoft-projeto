package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.PropostaTCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.PropostaTCC;
import com.psoft.tccmatch.repository.AreaEstudoRepository;
import com.psoft.tccmatch.repository.TCCRepository;
import com.psoft.tccmatch.util.ErroAreaEstudo;
import com.psoft.tccmatch.util.ErroTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaTCCServiceImpl implements PropostaTCCService {
    @Autowired
    private TCCRepository tccRepository;
    @Autowired
    private AreaEstudoRepository areaEstudoRepository;

    @Override
    public PropostaTCC criar(PropostaTCCDTO dto) throws ApiException {
        Optional<PropostaTCC> tcc_existe = tccRepository.findByTitulo(dto.getTitulo());

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

        PropostaTCC propostaTcc = new PropostaTCC(dto.getTitulo(), dto.getDescricao(), dto.getStatus(), areas);
        return tccRepository.save(propostaTcc);
    }

    @Override
    public PropostaTCC getById(Long id) throws ApiException {
        Optional<PropostaTCC> tccOpt = tccRepository.findById(id);

        if(tccOpt.isEmpty()){
            throw ErroTCC.erroTCCNaoExiste();
        }

        return tccOpt.get();
    }
    
    public List<PropostaTCC> getAll() { return tccRepository.findAll(); }
}
