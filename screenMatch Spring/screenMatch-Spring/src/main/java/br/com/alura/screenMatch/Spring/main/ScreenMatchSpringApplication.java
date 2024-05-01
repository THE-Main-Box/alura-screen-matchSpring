package br.com.alura.screenMatch.Spring.main;

import br.com.alura.screenMatch.Spring.model.record.EpisodeData;
import br.com.alura.screenMatch.Spring.model.record.SeasonData;
import br.com.alura.screenMatch.Spring.model.record.SeriesData;
import br.com.alura.screenMatch.Spring.service.processdata_related.GetSeriesData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenMatchSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenMatchSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {

        GetSeriesData getSeriesData = new GetSeriesData();
        Menu menu = new Menu();

        SeriesData seriesData;
        SeasonData seasonData;

        String serie;

        serie = menu.setSerie();

        seriesData = getSeriesData.getData(serie);

        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= seriesData.totalSeasons(); i++) {
            seasonData = getSeriesData.getData(serie, i);
            seasons.add(seasonData);
        }

        menu.showSeriesData(seriesData);
        menu.showSeasonData(seasons);
        menu.showTopFiveEp();

    }

}
