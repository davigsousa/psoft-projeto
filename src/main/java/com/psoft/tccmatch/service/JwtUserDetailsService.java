package com.psoft.tccmatch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.psoft.tccmatch.model.AdminUser;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.model.User;
import com.psoft.tccmatch.repository.AdminRepository;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        List<String> permissoes = new ArrayList<>();

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

        if (user instanceof Aluno || user instanceof AdminUser) {
            permissoes.add("ALUNO");
        }
        if (user instanceof Professor || user instanceof AdminUser) {
            permissoes.add("PROFESSOR");
        }

        List<GrantedAuthority> grantedAuthorities = permissoes.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getSenha(), grantedAuthorities
        );
    }
}