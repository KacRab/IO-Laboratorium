package org.example.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiazkaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaKsiazkaDao implements KsiazkaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ksiazka> findAll() {
        return entityManager.createQuery(
                "select k from Ksiazka k").getResultList();
    }

    @Override
    public Ksiazka findById(int id) {return entityManager.find(Ksiazka.class, id);}

    @Override
    public List<Ksiazka> findByAutor(Autor a) {
        return entityManager
                .createQuery("select k from Ksiazka k where k.autor=:autor")
                .setParameter("autor", a)
                .getResultList();
    }

    @Override
    public List<Ksiazka> findByKsiegarnia(Ksiegarnia k) {
        return entityManager.createQuery(
                "select k from Ksiazka k inner join k.ksiegarnie ksiegarnia where ksiegarnia=:ksiegarnia")
                .setParameter("ksiegarnia", k)
                .getResultList();
    }

    @Override
    @Transactional
    public Ksiazka add(Ksiazka k) {
        entityManager.persist(k);
        return k;
    }
}
