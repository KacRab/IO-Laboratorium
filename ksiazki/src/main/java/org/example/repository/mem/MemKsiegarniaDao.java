package org.example.repository.mem;

import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiegarniaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("ksiegarniaDao")
//@Primary
public class MemKsiegarniaDao implements KsiegarniaDao {

    @Override
    public List<Ksiegarnia> findAll() {
        return SampleData.ksiegarnie;
    }

    @Override
    public Ksiegarnia findById(int id) {
        return SampleData.ksiegarnie.stream().filter(k -> k.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Ksiegarnia> findByKsiazka(Ksiazka k) {
        return SampleData.ksiegarnie.stream().filter(ks -> ks.getKsiazki().contains(k)).collect(Collectors.toList());
    }
}
