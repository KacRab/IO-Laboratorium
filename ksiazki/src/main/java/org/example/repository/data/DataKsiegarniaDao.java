package org.example.repository.data;

import lombok.RequiredArgsConstructor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiegarniaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataKsiegarniaDao implements KsiegarniaDao {

    private final KsiegarniaRepository repository;

    @Override
    public List<Ksiegarnia> findAll() {
        return repository.findAll();
    }

    @Override
    public Ksiegarnia findById(int id) {return repository.findById(id).orElse(null);}

    @Override
    public List<Ksiegarnia> findByKsiazka(Ksiazka k) {return repository.findAllByKsiazka(k);}

    @Override
    public Ksiegarnia save(Ksiegarnia ksiegarnia) {return repository.save(ksiegarnia);}
}
