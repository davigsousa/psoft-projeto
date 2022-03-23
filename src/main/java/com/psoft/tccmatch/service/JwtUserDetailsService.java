package com.psoft.tccmatch.service;

import java.util.ArrayList;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.repository.AdminRepository;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        user = adminRepository.findByEmail(username).orElse(null);
        if (user == null) {
            user = professorRepository.findByEmail(username).orElse(null);
        }
        if (user == null) {
            user = alunoRepository.findByEmail(username).orElse(null);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com esse e-mail: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getSenha(), new ArrayList<>()
        );
    }
}