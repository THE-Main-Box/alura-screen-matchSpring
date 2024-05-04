package br.com.alura.screenMatch.Spring.model.classes;

import java.time.LocalDate;

public class Episodes {

    private String title;
    private int episodeNumber;
    private int seasonNumber;
    private double rating;
    private LocalDate released;

    public Episodes(String title, int episode, int season, String rating, String released) {
        this.title = title;
        this.seasonNumber = season;
        this.episodeNumber = episode;

        if (released.equalsIgnoreCase("n/a")) {
            this.released = null;
        } else {
            this.released = LocalDate.parse(released);
        }

        if (rating.equalsIgnoreCase("n/a")) {
            this.rating = 0.0;
        } else {
            this.rating = Double.parseDouble(rating);
        }

    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public double getRating() {
        return rating;
    }

    public LocalDate getReleased() {
        return released;
    }
}
