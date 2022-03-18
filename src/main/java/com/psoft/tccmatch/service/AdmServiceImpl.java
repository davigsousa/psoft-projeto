package com.psoft.tccmatch.service;

import com.psoft.tccmatch.DTO.AdmDTO;
import com.psoft.tccmatch.model.AdminUser;
import com.psoft.tccmatch.repository.AdmRepository;
import com.psoft.tccmatch.util.ErroAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmServiceImpl implements AdmService {
    @Autowired
    private AdmRepository admRepository;

    @Override
    public AdminUser criar_coordenador(AdmDTO dto) throws Exception {
        Optional<AdminUser> coord_existe = admRepository.findByEmail(dto.getEmail());

        if (coord_existe.isPresent()) {
            throw ErroAdmin.erroUsuarioJaExiste();
        }

        AdminUser novo_coord = new AdminUser(dto.getNome(), dto.getEmail(), dto.getSenha());
        novo_coord.setCargo("COORDERNADOR");
        return admRepository.save(novo_coord);
    }

    @Override
    public AdminUser criar_adm(AdmDTO dto) throws Exception {
        Optional<AdminUser> coord_existe = admRepository.findByEmail(dto.getEmail());

        if (coord_existe.isPresent()) {
            throw ErroAdmin.erroUsuarioJaExiste();
        }

        AdminUser novo_admin = new AdminUser(dto.getNome(), dto.getEmail(), dto.getSenha());
        novo_admin.setCargo("ADMINISTRADOR");
        return admRepository.save(novo_admin);
    }
}
