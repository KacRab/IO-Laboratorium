package org.example.repository.dummy;

import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiegarniaDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyKsiegarniaDao implements KsiegarniaDao {

    @Override
    public List<Ksiegarnia> findAll() {return List.of();}

    @Override
    public Ksiegarnia findById(int id) {return null;}

    @Override
    public List<Ksiegarnia> findByKsiazka(Ksiazka k) {return List.of();}
}
