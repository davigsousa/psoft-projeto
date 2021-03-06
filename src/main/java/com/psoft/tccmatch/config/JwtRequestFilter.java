package com.psoft.tccmatch.config;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psoft.tccmatch.model.AdminUser;
import com.psoft.tccmatch.model.Aluno;
import com.psoft.tccmatch.model.Professor;
import com.psoft.tccmatch.repository.AdminRepository;
import com.psoft.tccmatch.repository.AlunoRepository;
import com.psoft.tccmatch.repository.ProfessorRepository;
import com.psoft.tccmatch.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

                Object user = new Object();
                Optional<AdminUser> admin = adminRepository.findByEmail(username);
                if (admin.isEmpty()) {
                    Optional<Aluno> aluno = alunoRepository.findByEmail(username);
                    if (aluno.isEmpty()) {
                        Optional<Professor> professor = professorRepository.findByEmail(username);
                        if (professor.isEmpty()) {
                            System.out.println("Usu??rio n??o encontrado.");
                        } else {
                            user = professor.get();
                        }
                    } else {
                        user = aluno.get();
                    }
                } else {
                    user = admin.get();
                }

                request.setAttribute("user", user);
            } catch (IllegalArgumentException e) {
                System.out.println("Indispon??vel para obter token");
            } catch (ExpiredJwtException e) {
                System.out.println("Token expirou");
            }
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }


        chain.doFilter(request, response);
    }

}