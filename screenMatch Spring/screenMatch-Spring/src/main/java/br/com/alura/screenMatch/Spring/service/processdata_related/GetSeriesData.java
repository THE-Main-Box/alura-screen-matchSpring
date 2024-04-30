package br.com.alura.screenMatch.Spring.service.processdata_related;

import br.com.alura.screenMatch.Spring.model.EpisodeData;
import br.com.alura.screenMatch.Spring.model.SeasonData;
import br.com.alura.screenMatch.Spring.model.SeriesData;
import br.com.alura.screenMatch.Spring.service.api_related.ConsultApi;
import br.com.alura.screenMatch.Spring.service.api_related.UrlGenerator;
import br.com.alura.screenMatch.Spring.service.processdata_related.ConvertSeriesData;

public class GetSeriesData {

    private UrlGenerator generateUrl;
    private ConsultApi consultApi;
    private ConvertSeriesData convert;

    public GetSeriesData(){
        generateUrl = new UrlGenerator();
        consultApi = new ConsultApi();
        convert = new ConvertSeriesData();
    }

//    pegam dados específicos de uma série, temporada e episódios específicados na chamada dos métodos

//    *não pega dados de uma série única se quiser fazer isso terá de especificar a série em uma string e
//    passa-la como parametro em todos os métodos*

    public SeriesData getData(String serie){
        String encodedUrlSerie = generateUrl.createOmdbUrl(serie);
        String json = consultApi.getData(encodedUrlSerie);
        return convert.convertData(json, SeriesData.class);
    }

    public SeasonData getData(String serie, int season){
        String encodedUrlSeason = generateUrl.createOmdbUrl(serie, season);
        String json = consultApi.getData(encodedUrlSeason);
        return convert.convertData(json, SeasonData.class);
    }

    public EpisodeData getData(String serie, int season, int ep){
        String encodedUrlepisode = generateUrl.createOmdbUrl(serie, season, ep);
        String json = consultApi.getData(encodedUrlepisode);
        return convert.convertData(json, EpisodeData.class);
    }

}
