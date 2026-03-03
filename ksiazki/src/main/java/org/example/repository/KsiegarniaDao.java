package org.example.repository;

import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;

import java.util.List;

public interface KsiegarniaDao {

    List<Ksiegarnia> findAll();

    Ksiegarnia findById(int id);

    List<Ksiegarnia> findByKsiazka(Ksiazka k);
}
