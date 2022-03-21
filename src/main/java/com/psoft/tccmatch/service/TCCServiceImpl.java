package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.TCCDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.Laboratorio;
import com.psoft.tccmatch.model.TCC;
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
public class TCCServiceImpl implements TCCService{
    @Autowired
    private TCCRepository tccRepository;
    @Autowired
    private AreaEstudoRepository areaEstudoRepository;

    @Override
    public TCC criar(TCCDTO dto) throws ApiException {
        Optional<TCC> tcc_existe = tccRepository.findByTitulo(dto.getTitulo());

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

        TCC tcc = new TCC(dto.getTitulo(), dto.getDescricao(), dto.getStatus(), areas);
        return tccRepository.save(tcc);
    }
}
