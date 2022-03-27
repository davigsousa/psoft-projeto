package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.repository.AreaEstudoRepository;
import com.psoft.tccmatch.util.ErroAreaEstudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaEstudoServiceImpl implements AreaEstudoService{
    @Autowired
    private AreaEstudoRepository areaEstudoRepository;

    @Override
    public AreaEstudo getByAssunto(String assunto) throws ApiException {
        Optional<AreaEstudo> area_opt = areaEstudoRepository.findByAssunto(assunto);
        if (area_opt.isEmpty()) {
            throw ErroAreaEstudo.erroAreaNaoExiste();
        }
        return area_opt.get();
    }

    @Override
    public AreaEstudo getById(Long id) throws ApiException {
        Optional<AreaEstudo> area_opt = areaEstudoRepository.findById(id);
        if (area_opt.isEmpty()) {
            throw ErroAreaEstudo.erroAreaNaoExiste();
        }
        return area_opt.get();
    }

    @Override
    public AreaEstudo create(AreaDeEstudoDTO dto) throws ApiException {
        Optional<AreaEstudo> area_opt = areaEstudoRepository.findByAssunto(dto.getAssunto());
        if (area_opt.isPresent()) {
            throw ErroAreaEstudo.erroAreaJaExiste();
        }
        AreaEstudo area_estudo = new AreaEstudo(dto.getAssunto());
        areaEstudoRepository.save(area_estudo);
        return area_estudo;
    }

    @Override
    public List<AreaEstudo> getAll() {
        return areaEstudoRepository.findAll();
    }

    @Override
    public void selecionar(Long areaId, Object user) throws ApiException {

    }

    @Override
    public void desselecionar(Long areaId, Object user) throws ApiException {

    }
}
