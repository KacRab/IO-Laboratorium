package org.example.repository.data;

import lombok.RequiredArgsConstructor;
import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiazkaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataKsiazkaDao implements KsiazkaDao {

    private final KsiazkaRepository repository;

    @Override
    public List<Ksiazka> findAll() {return repository.findAll();}

    @Override
    public Ksiazka findById(int id) {return repository.findById(id).orElse(null);}

    @Override
    public List<Ksiazka> findByAutor(Autor a) {return repository.findAllByAutor(a);}

    @Override
    public List<Ksiazka> findByKsiegarnia(Ksiegarnia k) {return repository.findAllByKsiegarnieContaining(k);}

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public Ksiazka add(Ksiazka k) { return  repository.save(k);}
}
