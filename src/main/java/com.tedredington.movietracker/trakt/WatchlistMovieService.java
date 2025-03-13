package com.tedredington.movietracker.trakt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class WatchlistMovieService {

    private final RestClient traktMovieRestClient;
    private final Logger logger = LoggerFactory.getLogger(WatchlistMovieService.class);


    public WatchlistMovieService(RestClient traktMovieRestClient) {
        this.traktMovieRestClient = traktMovieRestClient;
    }

    public List<WatchlistMovie> getWatchlistMovies() {
        List<WatchlistMovie> response = traktMovieRestClient.get()
                .uri((uriBuilder -> {
                    uriBuilder.path("/watchlist/movies");
                    return uriBuilder.build();
                }))
                .retrieve()
                .body(new ParameterizedTypeReference<List<WatchlistMovie>>() {
                });
        logger.info(response.toString());
        return response;
    }
}
