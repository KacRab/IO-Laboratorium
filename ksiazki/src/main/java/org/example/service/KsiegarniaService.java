package org.example.service;

import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;

import java.util.List;

public interface KsiegarniaService {

    Ksiegarnia getKsiegarniaById(int id);

    List<Ksiegarnia> getAllKsiegarnie();

    List<Ksiegarnia> getKsiegarnieByKsiazka(Ksiazka m);

    List<Ksiazka> getKsiazkiInKsiegarnia(Ksiegarnia c);

    Ksiegarnia addKsiegarnia(Ksiegarnia ksiegarnia);
}
