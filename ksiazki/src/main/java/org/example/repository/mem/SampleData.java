package org.example.repository.mem;

import org.example.model.Autor;
import org.example.model.Ksiazka;
import org.example.model.Ksiegarnia;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

    static List<Ksiegarnia> ksiegarnie = new ArrayList<>();

    static List<Autor> autorzy = new ArrayList<>();

    static List<Ksiazka> ksiazki = new ArrayList<>();

    static {

        Autor sapkowski = new Autor(1, "Andrzej", "Sapkowski");
        Autor tokarczuk = new Autor(2, "Olga", "Tokarczuk");
        Autor lem = new Autor(3, "Stanislaw", "Lem");
        Autor mickiewicz = new Autor(4, "Adam", "Mickiewicz");

        Ksiazka wiedzmin = new Ksiazka(1, "Wiedzmin: Ostatnie zyczenie",
                "https://ecsmedia.pl/c/ostatnie-zyczenie-wiedzmin-tom-1-b-iext71634375.jpg",
                sapkowski, (float) 4.8);

        Ksiazka krewElfow = new Ksiazka(2, "Krew elfow",
                "https://ecsmedia.pl/c/krew-elfow-wiedzmin-tom-3-b-iext71634377.jpg",
                sapkowski, (float) 4.7);

        Ksiazka bieguni = new Ksiazka(3, "Bieguni",
                "https://ecsmedia.pl/c/bieguni-b-iext71252729.jpg",
                tokarczuk, (float) 4.3);

        Ksiazka ksiegiJakubowe = new Ksiazka(4, "Ksiegi Jakubowe",
                "https://ecsmedia.pl/c/ksiegi-jakubowe-b-iext71354589.jpg",
                tokarczuk, (float) 4.6);

        Ksiazka solaris = new Ksiazka(5, "Solaris",
                "https://ecsmedia.pl/c/solaris-b-iext71624861.jpg",
                lem, (float) 4.9);

        Ksiazka cyberiada = new Ksiazka(6, "Cyberiada",
                "https://ecsmedia.pl/c/cyberiada-b-iext71624858.jpg",
                lem, (float) 4.5);

        Ksiazka dziady = new Ksiazka(7, "Dziady",
                "https://ecsmedia.pl/c/dziady-b-iext71625355.jpg",
                mickiewicz, (float) 4.4);

        Ksiazka panTadeusz = new Ksiazka(8, "Pan Tadeusz",
                "https://ecsmedia.pl/c/pan-tadeusz-b-iext71625357.jpg",
                mickiewicz, (float) 4.6);

        bind(wiedzmin, sapkowski);
        bind(krewElfow, sapkowski);

        bind(bieguni, tokarczuk);
        bind(ksiegiJakubowe, tokarczuk);

        bind(solaris, lem);
        bind(cyberiada, lem);

        bind(dziady, mickiewicz);
        bind(panTadeusz, mickiewicz);

        Ksiegarnia empik = new Ksiegarnia(1, "Empik", "https://upload.wikimedia.org/wikipedia/commons/7/70/Empik_logo_2013.png");
        Ksiegarnia swiatKsiazki = new Ksiegarnia(2, "Swiat Ksiazki", "https://upload.wikimedia.org/wikipedia/commons/4/4f/Swiat_Ksiazki_logo.png");
        Ksiegarnia taniaksiazka = new Ksiegarnia(3, "TaniaKsiazka.pl", "https://www.taniaksiazka.pl/images/logo.png");
        Ksiegarnia znak = new Ksiegarnia(4, "Ksiegarnia Znak", "https://www.znak.com.pl/images/logo.png");

        bind(empik, wiedzmin);
        bind(taniaksiazka, wiedzmin);
        bind(taniaksiazka, bieguni);
        bind(taniaksiazka, solaris);

        bind(empik, cyberiada);
        bind(znak, cyberiada);
        bind(znak, dziady);
        bind(swiatKsiazki, dziady);
        bind(swiatKsiazki, bieguni);

        ksiazki.add(wiedzmin);
        ksiazki.add(krewElfow);
        ksiazki.add(bieguni);
        ksiazki.add(ksiegiJakubowe);
        ksiazki.add(solaris);
        ksiazki.add(cyberiada);
        ksiazki.add(dziady);
        ksiazki.add(panTadeusz);

        autorzy.add(sapkowski);
        autorzy.add(tokarczuk);
        autorzy.add(lem);
        autorzy.add(mickiewicz);

        ksiegarnie.add(empik);
        ksiegarnie.add(swiatKsiazki);
        ksiegarnie.add(taniaksiazka);
        ksiegarnie.add(znak);

    }

    private static void bind(Ksiegarnia k, Ksiazka ksiazka) {
        k.addKsiazka(ksiazka);
        ksiazka.addKsiegarnia(k);
    }

    private static void bind(Ksiazka k, Autor a) {
        a.addKsiazka(k);
        k.setAutor(a);
    }
}
