package br.com.alura.screenMatch.Spring.model.classes;

import br.com.alura.screenMatch.Spring.model.record.SeriesData;
import jakarta.persistence.*;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    private String poster;
    private String sinopsys;
    private String releaseYear;

    private int totalSeasons;
    private double rating;

    @Enumerated(EnumType.STRING)
    private Category genre;

    private String actors;

    public Serie(SeriesData serie) {
        this.actors = serie.actors();
        this.sinopsys = serie.sinopsys();
        this.releaseYear = serie.releaseYear();
        this.poster = serie.poster();
        this.title = serie.title();
        try {
            this.rating = Double.parseDouble(serie.rating());
            this.totalSeasons = serie.totalSeasons();
        }catch(Exception e){
            this.rating = 0.0;
            this.totalSeasons = 0;
        }
        this.setGenre(serie.genre());
    }

    /*Busca no enum alguma categoria de genero que exista que correspona à algum genero passado como string pelo serieData
     * Convertendo em uma lista e depois verificando se já está setado caso não é atribuido à this.genre*/
    private void setGenre(String genreToSplit) {
        String[] genres = genreToSplit.split(",");
        this.genre = null;

        for (int i = 0; i < genres.length; i++) {
            try {
                this.genre = Category.fromString(genres[i].trim());
                break; // Sai do loop assim que encontrar o primeiro gênero válido
            } catch (IllegalArgumentException e) {
                // Ignore o gênero não encontrado no enum
                if (i == genres.length - 1) { // Se chegou ao último gênero e nenhum foi válido
                    this.genre = null; // Define o gênero como null
                }

            }

        }

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Category getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getPoster() {
        return poster;
    }

    public String getSinopsys() {
        return sinopsys;
    }

 public String getActors() {
        return actors;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }
}
