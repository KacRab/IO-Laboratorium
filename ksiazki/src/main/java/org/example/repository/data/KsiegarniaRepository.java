package org.example.repository.data;

import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KsiegarniaRepository extends JpaRepository<Ksiegarnia,Integer> {

    List<Ksiegarnia> findAllByNameContaining(String name);

    @Query("select c from Ksiegarnia c inner join c.ksiazki ksiazka where ksiazka=:ksiazka")
    List<Ksiegarnia> findAllByKsiazka(@Param("ksiazka") Ksiazka ksiazka);
}
