package br.com.alura.screenMatch.Spring.service.api_related;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlGenerator {

    private String url, toSearch;
    private final String ADRESS, API_KEY, SEASON, EPISODE;

    public UrlGenerator(){
        ADRESS = "https://www.omdbapi.com/?t=";
        API_KEY = "&apikey=ef61e071";
        SEASON = "&Season=";
        EPISODE = "&episode=";
    }

    public String createOmdbUrl(String series, int season) {
        toSearch = this.encode(series);
        url = ADRESS + toSearch + SEASON + season + API_KEY;
//        "https://www.omdbapi.com/?t= %s &Season= %d &apikey=ef61e071".formatted(toSearch, season);
        return url;
    }

    public String createOmdbUrl(String series) {
        toSearch = this.encode(series);
        url = ADRESS + toSearch + API_KEY;
//                "https://www.omdbapi.com/?t= %s &apikey=ef61e071".formatted(toSearch);
        return url;
    }

    public String createOmdbUrl(String series, int season, int episode) {
        toSearch = this.encode(series);
        url = ADRESS + toSearch + SEASON + season + EPISODE + episode + API_KEY;
//                "https://www.omdbapi.com/?t= %s &Season= %d &episode= %d &apikey=ef61e071".formatted(toSearch, season, episode);
        return url;
    }

    private String encode(String media){
        return URLEncoder.encode(media, StandardCharsets.UTF_8);
    }

}
