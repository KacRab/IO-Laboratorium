package org.example.service.impl;

import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;
import org.example.repository.AutorDao;
import org.example.repository.KsiazkaDao;
import org.example.repository.KsiegarniaDao;
import org.example.service.KsiazkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class KsiazkaServiceBean implements KsiazkaService {

    private static final Logger log = Logger.getLogger(KsiazkaService.class.getName());

    //@Autowired
    private AutorDao autorDao;
    private KsiegarniaDao ksiegarniaDao;
    private KsiazkaDao ksiazkaDao;

    public KsiazkaServiceBean(AutorDao autorDao, KsiegarniaDao ksiegarniaDao, KsiazkaDao ksiazkaDao) {
        this.autorDao = autorDao;
        this.ksiegarniaDao = ksiegarniaDao;
        this.ksiazkaDao = ksiazkaDao;
    }

    @Autowired
    public void setAutorDao(AutorDao autorDao) {
        this.autorDao = autorDao;
    }

    public List<Ksiazka> getAllKsiazki() {
        log.info("searching all ksiazki...");
        return ksiazkaDao.findAll();
    }

    public List<Ksiazka> getKsiazkiByAutor(Autor d) {
        log.info("serching ksiazki by autor " + d.getId());
        return ksiazkaDao.findByAutor(d);
    }

    public List<Ksiazka> getKsiazkiInKsiegarnia(Ksiegarnia c) {
        log.info("searching ksiazki in ksiegarnia " + c.getId());
        return ksiazkaDao.findByKsiegarnia(c);
    }

    public Ksiazka getKsiazkaById(int id) {
        log.info("searching ksiazka by id " + id);
        return ksiazkaDao.findById(id);
    }

    public List<Ksiegarnia> getAllKsiegarnie() {
        log.info("searching all ksiegarnie");
        return ksiegarniaDao.findAll();
    }

    public List<Ksiegarnia> getKsiegarnieByKsiazka(Ksiazka m) {
        log.info("searching ksiegarnie by ksiazka " + m.getId());
        return ksiegarniaDao.findByKsiazka(m);
    }

    public Ksiegarnia getKsiegarniaById(int id) {
        log.info("searching ksiegarnia by id " + id);
        return ksiegarniaDao.findById(id);
    }

    public List<Autor> getAllAutorzy() {
        log.info("searching all autorzy");
        return autorDao.findAll();
    }

    public Autor getAutorById(int id) {
        log.info("searching autor by id " + id);
        return autorDao.findById(id);
    }

    public Ksiazka addKsiazka(Ksiazka m) {
        log.info("about to add ksiazka " + m);
        return ksiazkaDao.add(m);
    }

    public Autor addAutor(Autor d) {
        log.info("about to add autor " + d);
        return autorDao.add(d);
    }
}
