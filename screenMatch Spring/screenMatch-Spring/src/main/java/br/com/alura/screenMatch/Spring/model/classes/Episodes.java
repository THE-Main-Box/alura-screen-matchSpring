package br.com.alura.screenMatch.Spring.model.classes;

public class Episodes {

    private String title;
    private int episodeNumber;
    private int seasonNumber;
    private String rating;
    private String released;

    public Episodes(String title, int episode, int season, String rating, String released) {
        this.title = title;
        this.seasonNumber = season;
        this.episodeNumber = episode;
        this.rating = rating;
        this.released = released;
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

    public String getRating() {
        return rating;
    }

    public String getReleased() {
        return released;
    }
}
