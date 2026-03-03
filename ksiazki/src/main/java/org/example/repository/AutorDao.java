package org.example.repository;

import org.example.model.Autor;

import java.util.List;

public interface AutorDao {

    List<Autor> findAll();

    Autor findById(int id);

    Autor add(Autor a);


}
