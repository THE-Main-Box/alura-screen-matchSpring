package br.com.alura.screenMatch.Spring.model;


import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(

@JsonAlias("Season")        Integer seasonNumber,
@JsonAlias("Episodes")      List<EpisodeData> episodes

){}
