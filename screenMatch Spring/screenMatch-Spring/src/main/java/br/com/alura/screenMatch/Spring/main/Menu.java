package br.com.alura.screenMatch.Spring.main;

import br.com.alura.screenMatch.Spring.model.classes.Episodes;
import br.com.alura.screenMatch.Spring.model.record.EpisodeData;
import br.com.alura.screenMatch.Spring.model.record.SeasonData;
import br.com.alura.screenMatch.Spring.model.record.SeriesData;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    Scanner reader;
    List<Episodes> episodesList = new ArrayList<>();

    public Menu() {
        reader = new Scanner(System.in);
    }

    public String setSerie() {
        System.out.println("***************************");
        System.out.println("Digite uma Série que você goste");
        System.out.println("***************************");

        return reader.nextLine();
    }

    public void showSeasonData(List<SeasonData> seasons) {

        seasons.forEach(s -> {

            System.out.println("Temporada: " + s.seasonNumber());
            System.out.println("***************************");

            s.episodes().forEach(e -> {
                if(!e.rating().equalsIgnoreCase("n/a")) {
                    episodesList.add(new Episodes(e.title(), e.episodeNumber(), s.seasonNumber(), e.rating(), e.released()));
                }
                System.out.println("Titulo: " + e.title() + " Episódio: " + e.episodeNumber());
            });

            System.out.println("***************************");

        });

    }

    public void showTopFiveEp() {

        List<Episodes> topEpisodes = episodesList.stream()
                .sorted((e1, e2) -> e2.getRating().compareTo(e1.getRating()))
                .limit(5)
                .toList();

        topEpisodes.forEach(e -> {
            System.out.println("***************************");
            System.out.println("Titulo...:  " + e.getTitle());
            System.out.println("Episódio.:  " + e.getEpisodeNumber());
            System.out.println("Temporada:  " + e.getSeasonNumber());
            System.out.println("Nota.....:  " + e.getRating());
            System.out.println("***************************");
        });

    }


    public void showSeriesData(SeriesData serie) {

        System.out.println("***************************");

        System.out.println(serie);

        System.out.println("***************************");

    }


}
