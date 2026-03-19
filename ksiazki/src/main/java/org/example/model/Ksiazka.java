package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ksiazka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String cover;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private float rating;
    @ManyToMany
    @JoinTable(
            name = "ksiazka_ksiegarnia",
            joinColumns = @JoinColumn(name = "ksiazka_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ksiegarnia_id", referencedColumnName = "id")
    )

    private List<Ksiegarnia> ksiegarnie = new ArrayList<>();

    public Ksiazka(int id, String title, String poster, Autor autor, float rating) {
        this.id = id;
        this.title = title;
        this.cover = poster;
        this.autor = autor;
        this.rating = rating;
    }

    public Ksiazka() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Ksiegarnia> getKsiegarnie() {
        return ksiegarnie;
    }

    public void setKsiegarnie(List<Ksiegarnia> cinemas) {
        this.ksiegarnie = cinemas;
    }

    public void addKsiegarnia(Ksiegarnia k) {
        this.ksiegarnie.add(k);
    }


   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (Float.compare(movie.rating, rating) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return poster != null ? poster.equals(movie.poster) : movie.poster == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }*/

    @Override
    public String toString() {
        return "Ksiazka{" +
                "title='" + title + '\'' +
                ", autor=" + autor +
                ", rating=" + rating +
                '}';
    }
}
