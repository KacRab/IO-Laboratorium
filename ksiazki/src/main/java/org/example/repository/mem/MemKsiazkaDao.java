package org.example.repository.mem;

import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiazkaDao;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemKsiazkaDao implements KsiazkaDao {
    @Override
    public List<Ksiazka> findAll() {
        return SampleData.ksiazki;
    }

    @Override
    public Ksiazka findById(int id) {
        return SampleData.ksiazki.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Ksiazka> findByAutor(Autor a) {
        return SampleData.ksiazki.stream().filter(m -> m.getAutor() == a).collect(Collectors.toList());
    }

    @Override
    public List<Ksiazka> findByKsiegarnia(Ksiegarnia k) {
        return SampleData.ksiazki.stream().filter(m -> m.getKsiegarnie().contains(k)).collect(Collectors.toList());
    }

    @Override
    public Ksiazka add(Ksiazka k) {
        int max = SampleData.ksiazki.stream().max((m1, m2) -> m1.getId() - m2.getId()).get().getId();
        k.setId(++max);
        SampleData.ksiazki.add(k);
        return k;
    }
}
