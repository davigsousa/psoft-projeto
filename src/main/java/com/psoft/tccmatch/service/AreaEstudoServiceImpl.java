package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AreaDeEstudoDTO;
import com.psoft.tccmatch.exception.ApiException;
import com.psoft.tccmatch.model.AreaEstudo;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.processors.ManipularAreaEstudoProcessor.ManipularAreaEstudoProcessor;
import com.psoft.tccmatch.repository.AreaEstudoRepository;
import com.psoft.tccmatch.util.ErroAreaEstudo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AreaEstudoServiceImpl implements AreaEstudoService{
    @Autowired
    private AreaEstudoRepository areaEstudoRepository;

    private final Map<String, ManipularAreaEstudoProcessor> manipularAreaEstudoProcessors;

    public AreaEstudoServiceImpl(Map<String, ManipularAreaEstudoProcessor> manipularAreaEstudoProcessors) {
        this.manipularAreaEstudoProcessors = manipularAreaEstudoProcessors;
    }

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
    public void selecionar(Long areaId, User user) throws ApiException {
        AreaEstudo area = getById(areaId);
        String name = getProcessorName(user);
        manipularAreaEstudoProcessors.get(name).selecionarArea(area, user);
    }

    @Override
    public void desselecionar(Long areaId, User user) throws ApiException {
        AreaEstudo area = getById(areaId);
        String name = getProcessorName(user);
        manipularAreaEstudoProcessors.get(name).desselecionarArea(area, user);
    }

    private String getProcessorName(User user) {
        return user.getTipo().name() + "_Area";
    }
}
