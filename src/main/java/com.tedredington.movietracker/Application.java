package com.tedredington.movietracker;

import com.tedredington.movietracker.trakt.WatchedMovie;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(RestClient traktMovieRestClient){
        return args -> {
            List<WatchedMovie> response = traktMovieRestClient.get()
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<WatchedMovie>>() {
                    });
            System.out.println(response);
        };
    }

}

