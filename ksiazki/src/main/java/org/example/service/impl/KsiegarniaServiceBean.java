package org.example.service.impl;

import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.KsiazkaDao;
import org.example.repository.KsiegarniaDao;
import org.example.service.KsiegarniaService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class KsiegarniaServiceBean implements KsiegarniaService {

    private static final Logger log = Logger.getLogger(KsiegarniaService.class.getName());

    private KsiegarniaDao ksiegarniaDao;
    private KsiazkaDao ksiazkaDao;

    public KsiegarniaServiceBean(KsiegarniaDao ksiegarniaDao, KsiazkaDao ksiazkaDao) {
        log.info("creating ksiegarnia service bean");
        this.ksiegarniaDao = ksiegarniaDao;
        this.ksiazkaDao = ksiazkaDao;
    }

    public Ksiegarnia getKsiegarniaById(int id) {
        log.info("searching ksiegarnia by id " + id);
        return ksiegarniaDao.findById(id);
    }

    @Override
    public List<Ksiazka> getKsiazkiInKsiegarnia(Ksiegarnia c) {
        log.info("searching ksiazki in ksiegarnia " + c.getId());
        return ksiazkaDao.findByKsiegarnia(c);
    }

    public List<Ksiegarnia> getAllKsiegarnie() {
        log.info("searching all ksiegarnie");
        return ksiegarniaDao.findAll();
    }

    public List<Ksiegarnia> getKsiegarnieByKsiazka(Ksiazka m) {
        log.info("searching ksiegarnie by ksiazka " + m.getId());
        return ksiegarniaDao.findByKsiazka(m);
    }
}
