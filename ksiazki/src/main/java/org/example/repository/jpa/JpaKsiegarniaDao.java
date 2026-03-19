package org.example.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiegarniaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaKsiegarniaDao implements KsiegarniaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ksiegarnia> findAll() {
        // SQL -> HQL -> JPQL
        return entityManager
                .createQuery("select k from Ksiegarnia k")
                .getResultList();
    }

    @Override
    public Ksiegarnia findById(int id) {return entityManager.find(Ksiegarnia.class, id);}

    @Override
    public List<Ksiegarnia> findByKsiazka(Ksiazka k) {
        return entityManager
                .createQuery("select c from Ksiegarnia c inner join c.ksiazki ksiazka where ksiazka=:ksiazka")
                .setParameter("ksiazka", k)
                .getResultList();
    }

    @Override
    public Ksiegarnia save(Ksiegarnia ksiegarnia) {
        entityManager.persist(ksiegarnia);
        return ksiegarnia;
    }
}
