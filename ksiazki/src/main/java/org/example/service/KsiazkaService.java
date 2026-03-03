package org.example.service;

import org.example.model.Autor;
import org.example.model.Ksiazka;

import java.util.List;

public interface KsiazkaService {


    List<Ksiazka> getAllKsiazki();

    List<Ksiazka> getKsiazkiByAutor(Autor d);

    Ksiazka getKsiazkaById(int id);

    Ksiazka addKsiazka(Ksiazka m);


    List<Autor> getAllAutorzy();

    Autor getAutorById(int id);

    Autor addAutor(Autor d);
}
