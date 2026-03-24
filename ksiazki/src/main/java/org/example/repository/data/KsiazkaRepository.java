package org.example.repository.data;

import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KsiazkaRepository extends JpaRepository<Ksiazka,Integer> {

    List<Ksiazka> findAllByAutor(Autor a);

    List<Ksiazka> findAllByKsiegarnieContaining(Ksiegarnia k);
}
