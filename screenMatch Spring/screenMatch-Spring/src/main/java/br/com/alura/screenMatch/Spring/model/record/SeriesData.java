package br.com.alura.screenMatch.Spring.model.record;

import com.fasterxml.jackson.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData (

@JsonAlias("Title")         String title,
@JsonAlias("imdbRating")    String rating,
@JsonAlias("Released")      String releaseYear,
                            Integer totalSeasons,
@JsonAlias("Poster")        String poster,
@JsonAlias("Plot")          String sinopsys,
@JsonAlias("Genre")         String genre,
@JsonAlias("Actors")        String actors

){}
