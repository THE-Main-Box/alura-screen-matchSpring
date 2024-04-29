package br.com.alura.screenMatch.Spring.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UrlGenerator {

    private String url, toSearch;

    public String createOmdbUrl(String series, int season) {
        toSearch = this.encode(series);
        url = "https://www.omdbapi.com/?t=%s&Season=%d&apikey=ef61e071".formatted(toSearch, season);
        return url;
    }

    public String createOmdbUrl(String series) {
        toSearch = this.encode(series);
        url = "https://www.omdbapi.com/?t=%s&apikey=ef61e071".formatted(toSearch);
        return url;
    }

    private String encode(String media){
        return URLEncoder.encode(media, StandardCharsets.UTF_8);
    }

}
