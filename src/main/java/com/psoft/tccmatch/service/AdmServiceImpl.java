package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AdmDTO;
import com.psoft.tccmatch.model.Admin;
import com.psoft.tccmatch.repository.AdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmServiceImpl implements AdmService {
    @Autowired
    private AdmRepository admRepository;

    @Override
    public Admin criar_coordenador(AdmDTO dto) throws Exception {
        Optional<Admin> coord_existe = admRepository.findByEmail(dto.getEmail());

        if (coord_existe.isPresent()) {
            throw new Exception("Já existe um usuário com esse e-mail");
        }

        Admin novo_coord = new Admin(dto.getNome(), dto.getEmail(), dto.getSenha());
        novo_coord.setCargo("COORDERNADOR");
        return admRepository.save(novo_coord);
    }

    @Override
    public Admin criar_adm(AdmDTO dto) throws Exception {
        Optional<Admin> coord_existe = admRepository.findByEmail(dto.getEmail());

        if (coord_existe.isPresent()) {
            throw new Exception("Já existe um usuário com esse e-mail");
        }

        Admin novo_admin = new Admin(dto.getNome(), dto.getEmail(), dto.getSenha());
        novo_admin.setCargo("ADMINISTRADOR");
        return admRepository.save(novo_admin);
    }
}
