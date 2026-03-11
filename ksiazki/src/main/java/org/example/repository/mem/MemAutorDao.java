package org.example.repository.mem;

import org.example.model.Autor;
import org.example.repository.AutorDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemAutorDao implements AutorDao {
    @Override
    public List<Autor> findAll() {
        return SampleData.autorzy;
    }

    @Override
    public Autor findById(int id) {
        return SampleData.autorzy.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Autor add(Autor a) {
        int max = SampleData.autorzy.stream().max((a1, a2) -> a1.getId() - a2.getId()).get().getId();
        a.setId(++max);
        SampleData.autorzy.add(a);
        return a;
    }
}
