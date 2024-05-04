package br.com.alura.screenMatch.Spring.model.record;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData (

@JsonAlias("Title")         String title,
@JsonAlias("imdbRating")    String rating,
@JsonAlias("Released")      String releaseYear,
                            Integer totalSeasons

){}
