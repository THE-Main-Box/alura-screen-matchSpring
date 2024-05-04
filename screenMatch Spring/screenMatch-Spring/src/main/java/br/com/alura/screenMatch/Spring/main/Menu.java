package br.com.alura.screenMatch.Spring.main;

import br.com.alura.screenMatch.Spring.model.classes.Episodes;
import br.com.alura.screenMatch.Spring.model.record.EpisodeData;
import br.com.alura.screenMatch.Spring.model.record.SeasonData;
import br.com.alura.screenMatch.Spring.model.record.SeriesData;
import br.com.alura.screenMatch.Spring.service.processdata_related.GetSeriesData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {

    private String serie;
    private SeasonData seasonData;
    private SeriesData seriesData;
    private List<SeasonData> seasons;
    private Scanner reader = new Scanner(System.in);
    private GetSeriesData getSeriesData = new GetSeriesData();
    private List<Episodes> episodesList = new ArrayList<>();
    private List<Episodes> yearEpisodes = new ArrayList<>();

    private void setGeneralData(SeriesData serie, List<SeasonData> seasons) {
        this.seasons = seasons;
        this.seriesData = serie;

        this.seasons.forEach(s -> {
            s.episodes().forEach(e -> {
                episodesList.add(new Episodes(e.title(), e.episodeNumber(), s.seasonNumber(), e.rating(), e.released()));
            });
        });

    }


    /*  conjunto de processos que pegam os dados da api e os armazena em variáveis de escopo global
     *       além de pegar os episódios de uma temporada e armazená-los em uma lista*/
    public void setSerie() {
        System.out.println("***************************");
        System.out.println("Digite uma Série que você goste");
        System.out.println("***************************");

        try {
            serie = reader.nextLine();

            seriesData = getSeriesData.getData(serie);

            List<SeasonData> seasons = new ArrayList<>();

            for (int i = 1; i <= seriesData.totalSeasons(); i++) {
                seasonData = getSeriesData.getData(serie, i);
                seasons.add(seasonData);
            }

            this.setGeneralData(seriesData, seasons);
        } catch (Exception e) {
            System.out.println("Erro ao setar os dados da série: " + e);
            this.setSerie();
        }
    }


    /*    pega dados de uma lista de escopo global de temporadas e pra cada temporada pega os episódios
     *        e pra cada episódio se é mostrado seus respectivos dados*/
    public void showSeasonData() {

        Map<Integer, Double> seasonsRating = episodesList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episodes::getSeasonNumber,
                        Collectors.averagingDouble(Episodes::getRating)));

        seasons.forEach(s -> {

            System.out.println("Temporada:  " + s.seasonNumber());
            System.out.printf("Nota.....:  %.1f%n", seasonsRating.get(s.seasonNumber()));
            System.out.println("***************************");

            s.episodes().forEach(e -> {
                System.out.println("Titulo: " + e.title() + " Episódio: " + e.episodeNumber() + " Lançamento: " + e.released());
            });

            System.out.println("***************************");
        });
    }


    /*    utiliza uma lambda para selecionar os 5 melhores episódios de uma série e os mostra no final*/
    public void showTopFiveEp() {

        List<Episodes> topEpisodes = episodesList.stream()
                .sorted(Comparator.comparing(Episodes::getRating).reversed())
                .limit(5)
                .toList();
        topEpisodes.forEach(this::showEpisodeData);

    }


    //    mostra os dados gerais de uma série como a avaliação média e sua quantidade de episódios e temporadas
    public void showSeriesData() {

        DoubleSummaryStatistics est = episodesList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episodes::getRating));

        System.out.println("***************************");

        System.out.println("Série.........:   " + seriesData.title());
        System.out.println("Temporadas....:   " + seriesData.totalSeasons());
        System.out.println("Episódios.....:   " + episodesList.size());
        System.out.println("Lançamento....:   " + seriesData.releaseYear());
        System.out.println("Nota/Série....:   " + seriesData.rating());
        System.out.println("Média/Ep......:   %.1f".formatted(est.getAverage()));
        System.out.println("Média/Mlr/Ep..:   %.1f".formatted(est.getMax()));
        System.out.println("Média/Pior/Ep.:   %.1f".formatted(est.getMin()));

        System.out.println("***************************");

    }

    private List<Episodes> getEpisodeOnYear() {
        try {
            int year = Integer.parseInt(reader.nextLine());


            System.out.println("getEpisodeOnYear OK");
            return episodesList.stream()
                    .filter(e -> e.getReleased() != null && e.getReleased().getYear() == year)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("getEpisodeOnYear NotOk " + e);
            this.getEpisodeOnYear();
            return null;
        }

    }


    //    pergunta ao usuário qual o ano de um episódio e os coleta e os mostra no final
    public void showEpisodeOnYear() {
        System.out.println("***************************");
        System.out.println("Quer ver o episódio de que ano?");
        System.out.println("***************************");

        yearEpisodes = this.getEpisodeOnYear();

        yearEpisodes.forEach(this::showEpisodeData);
    }

    private void showEpisodeData(Episodes episode) {
        System.out.println("***************************");
        System.out.println("Titulo...:  " + episode.getTitle());
        System.out.println("Episódio.:  " + episode.getEpisodeNumber());
        System.out.println("Temporada:  " + episode.getSeasonNumber());
        System.out.println("Nota.....:  " + episode.getRating());
        System.out.println("Dt/lanc..:  " + episode.getReleased().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("***************************");
    }


    /*    realiza uma pesquisa simples para pegar o primeiro episódio que contém uma sequência
     *           de caractéres correspondente a seu nome*/
    public void searchEpisode() {
        System.out.println("***************************");
        System.out.println("qual o episódio em questão");
        System.out.println("***************************");

        String toSearch = reader.nextLine();

        try {
            Optional<Episodes> searchResult = episodesList.stream()
                    .filter(e -> e.getTitle().toUpperCase().contains(toSearch.toUpperCase()))
                    .findFirst();

            Episodes episode = searchResult.get();

            this.showEpisodeData(episode);

        } catch (Exception e) {
            System.out.println("searchEpisode NotOk " + e);
            this.searchEpisode();
        }

    }

}
