package br.com.alura.screenMatch.Spring.model;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData (
        @JsonAlias("Title")     String title,
                                String imdbRating,
        @JsonAlias("Released")  String releaseYear,
                                Integer totalSeasons
){}
