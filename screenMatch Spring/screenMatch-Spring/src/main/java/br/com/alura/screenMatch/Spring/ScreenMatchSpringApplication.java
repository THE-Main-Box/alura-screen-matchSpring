package br.com.alura.screenMatch.Spring;

import br.com.alura.screenMatch.Spring.model.SeriesData;
import br.com.alura.screenMatch.Spring.service.ConsultApi;
import br.com.alura.screenMatch.Spring.service.ConvertSeriesData;
import br.com.alura.screenMatch.Spring.service.UrlGenerator;
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

        UrlGenerator generateUrl = new UrlGenerator();
        ConsultApi consultApi = new ConsultApi();
        ConvertSeriesData convert = new ConvertSeriesData();

        String json;
        String encodedUrl;


        encodedUrl = generateUrl.createOmdbUrl("lost in space");
        json = consultApi.getData(encodedUrl);
        System.out.println(encodedUrl);
        SeriesData series = convert.convertData(json, SeriesData.class);

        System.out.println(series);


    }
}
