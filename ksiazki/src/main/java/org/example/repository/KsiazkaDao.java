package org.example.repository;

import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;

import java.util.List;

public interface KsiazkaDao {

    List<Ksiazka> findAll();

    Ksiazka findById(int id);

    List<Ksiazka> findByAutor(Autor a);

    List<Ksiazka> findByKsiegarnia(Ksiegarnia k);

    Ksiazka add(Ksiazka k);

}
