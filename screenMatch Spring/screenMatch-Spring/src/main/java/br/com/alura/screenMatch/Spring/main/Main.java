package br.com.alura.screenMatch.Spring.main;

import br.com.alura.screenMatch.Spring.model.classes.Episodes;
import br.com.alura.screenMatch.Spring.model.record.SeasonData;
import br.com.alura.screenMatch.Spring.model.record.SeriesData;
import br.com.alura.screenMatch.Spring.service.processdata_related.GetSeriesData;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private SeasonData seasonData;
    private SeriesData serieData;
    private GetSeriesData getSeriesData = new GetSeriesData();
    private List<Episodes> episodesList = new ArrayList<>();
    private List<Episodes> yearEpisodes = new ArrayList<>();
    private List<SeriesData> seriesDataList = new ArrayList<>();
    private List<SeasonData> seasons;
    private Scanner reader = new Scanner(System.in);
    private String serie;

    public void lobby() {

        int choice = -1;

        while (choice != 0) {
            System.out.println("***********DESEJA***********");
            System.out.println("[1] - Buscar Série");
            System.out.println("[2] - Buscar Episódio");
            System.out.println("[3] - Histórico de Série");
            System.out.println();
            System.out.println("[0] - Sair");
            System.out.println("***************************");

            choice = reader.nextInt();
            reader.nextLine();

            switch (choice) {
                case 0:
                    if (!seriesDataList.isEmpty()) {
                        this.showSeriesList();
                    }
                    break;
                case 1:
                    this.setSerie();
                    this.showSeriesData();
                    break;
                case 2:
                    this.setSerie();
                    this.showSeasonData();
                    break;
                case 3:
                    if (!seriesDataList.isEmpty()) {
                        this.showSeriesList();
                    } else {
                        System.out.println("Você ainda não pesquisou nenhuma série");
                    }
                    break;
                default:
                    System.out.println("Por favor escolha um opção válida");
                    break;
            }
        }
    }

    /*Mostra as Séries pesquisadas*/
    private void showSeriesList() {

        seriesDataList.forEach(sd -> {
            System.out.println("***************************");
            System.out.println("Titúlo.....:  " + sd.title());
            System.out.println("Temporadas.:  " + sd.totalSeasons());
            System.out.println("Ano........:  " + sd.releaseYear());
            System.out.println("Gênero.....:  " + sd.getGenreList());
            System.out.println("Nota.......:  " + sd.rating());
            System.out.println("***************************");
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
//          converte o nome da série passada em uma resposta da api em json e o converte  para o tipo correto
            serieData = getSeriesData.getData(serie);

            if (serieData.totalSeasons() != null && !seriesDataList.contains(serieData)) {
                seriesDataList.add(serieData);
            }

            List<SeasonData> seasons = new ArrayList<>();

            for (int i = 1; i <= serieData.totalSeasons(); i++) {
                seasonData = getSeriesData.getData(serie, i);
                seasons.add(seasonData);
            }


            this.seasons = seasons;

            this.seasons.forEach(s -> {
                s.episodes().forEach(e -> {
                    episodesList.add(new Episodes(e.title(), e.episodeNumber(), s.seasonNumber(), e.rating(), e.released()));
                });
            });

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

        DoubleSummaryStatistics estat = episodesList.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.summarizingDouble(Episodes::getRating));


        System.out.println("***************************");

        System.out.println("Série.........:   " + serieData.title());
        System.out.println("Gênero........:   " + serieData.getGenreList());
        System.out.println("Temporadas....:   " + serieData.totalSeasons());
        System.out.println("Episódios.....:   " + episodesList.size());
        System.out.println("Lançamento....:   " + serieData.releaseYear());
        System.out.println("Atores........:   " + serieData.getActorList());
        System.out.println("Nota/Série....:   " + serieData.rating());
        System.out.println("Sinópse.......:   " + serieData.sinopsys());
        System.out.println("//***//***//***//**//**//**//**");
        System.out.println("Poster........:       " + serieData.poster());
        System.out.println("Média/Ep......:   %.1f".formatted(estat.getAverage()));
        System.out.println("Média/Mlr/Ep..:   %.1f".formatted(estat.getMax()));
        System.out.println("Média/Pior/Ep.:   %.1f".formatted(estat.getMin()));

        System.out.println("***************************");

    }

    private List<Episodes> getEpisodeOnYear() {
        try {
            int year = Integer.parseInt(reader.nextLine());


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
