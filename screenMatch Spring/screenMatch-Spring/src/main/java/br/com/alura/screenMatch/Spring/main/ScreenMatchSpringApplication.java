package br.com.alura.screenMatch.Spring.main;

import br.com.alura.screenMatch.Spring.model.record.EpisodeData;
import br.com.alura.screenMatch.Spring.model.record.SeasonData;
import br.com.alura.screenMatch.Spring.model.record.SeriesData;
import br.com.alura.screenMatch.Spring.service.processdata_related.GetSeriesData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScreenMatchSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Menu menu = new Menu();

        menu.setSerie();

        menu.showSeriesData();
        menu.showSeasonData();
        menu.showTopFiveEp();
        menu.showEpisodeOnYear();
        menu.searchEpisode();
    }

}
