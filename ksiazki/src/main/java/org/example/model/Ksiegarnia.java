package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Ksiegarnia {

    private int id;
    private String name;
    private String logo;
    private List<Ksiazka> ksiazki = new ArrayList<>();

    public Ksiegarnia(int id, String name, String logo) {//konsturktor
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Ksiegarnia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Ksiazka> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<Ksiazka> ksiazki) {
        this.ksiazki = ksiazki;
    }

    public void addKsiazka(Ksiazka k) {
        this.ksiazki.add(k);
    }

    @Override
    public String toString() {
        return "Ksiegarnia{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
