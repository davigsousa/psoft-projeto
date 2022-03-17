package com.psoft.tccmatch.repository;

import com.psoft.tccmatch.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class AlunoRepositoryImpl implements AlunoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Aluno> findByMatricula(String matricula) {
        String queryStr = "SELECT a.* FROM aluno a WHERE a.matricula = :matricula";
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter("matricula", matricula);
            return (Optional<Aluno>) query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
