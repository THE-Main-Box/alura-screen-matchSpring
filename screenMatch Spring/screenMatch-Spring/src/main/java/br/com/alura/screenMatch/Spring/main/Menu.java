package br.com.alura.screenMatch.Spring.main;

import br.com.alura.screenMatch.Spring.model.EpisodeData;
import br.com.alura.screenMatch.Spring.model.SeasonData;
import br.com.alura.screenMatch.Spring.model.SeriesData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner reader;

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

        List<EpisodeData> episodes;
        for (int i = 0; i < seasons.size(); i++) {

            episodes = new ArrayList<>(seasons.get(i).episodes());

            System.out.println("Temporada: " + seasons.get(i).seasonNumber());
            System.out.println("***************************");

            for (EpisodeData ep : episodes) {
                System.out.println("Titulo: " + ep.title() + " Episódio: " + ep.episodeNumber());
            }

            System.out.println("***************************");
        }

    }

    public void showSeriesData(SeriesData serie) {

        System.out.println("***************************");

        System.out.println(serie);

        System.out.println("***************************");

    }


}
