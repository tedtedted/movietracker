package com.tedredington.movietracker.trakt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TraktMovieService {

    private final RestClient traktMovieRestClient;
    private final Logger logger = LoggerFactory.getLogger(TraktMovieService.class);

    public TraktMovieService(RestClient traktMovieRestClient) {
        this.traktMovieRestClient = traktMovieRestClient;
    }

    public List<WatchedMovie> getWatchedMovies(){
        List<WatchedMovie> response = traktMovieRestClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<WatchedMovie>>() {
                });
        logger.info(response.toString());
        return response;
    }
}