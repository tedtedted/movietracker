package com.tedredington.movietracker.trakt;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
class TraktClientConfig {

    @Value("${trakt.client_id}")
    private String TRAKT_CLIENT_ID;

    @Value("${trakt.base_url}")
    private String TRAKT_BASE_URL;

    @Value("${trakt.username}")
    private String TRAKT_USERNAME;

    @Value("${trakt.api_version}")
    private String TRAKT_API_VERSION;

    @Bean
    public RestClient traktMoviesRestClient() {
        return RestClient.builder()
                .baseUrl(TRAKT_BASE_URL+ TRAKT_USERNAME + "/history/movies")
                .defaultHeaders(
                        httpHeaders -> {
                            httpHeaders.set("content-type", MediaType.APPLICATION_JSON_VALUE);
                            httpHeaders.set("trakt-api-version", TRAKT_API_VERSION);
                            httpHeaders.set("trakt-api-key", TRAKT_CLIENT_ID);
                        })
                .build();
    }
}


