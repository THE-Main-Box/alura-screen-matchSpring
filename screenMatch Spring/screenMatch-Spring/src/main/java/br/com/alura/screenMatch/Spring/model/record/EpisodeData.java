package br.com.alura.screenMatch.Spring.model.record;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(

@JsonAlias("Title")         String title,
@JsonAlias("Episode")       Integer episodeNumber,
@JsonAlias("imdbRating")    String rating,
@JsonAlias("Released")      String released

){}
