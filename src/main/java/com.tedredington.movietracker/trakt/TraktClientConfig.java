package com.tedredington.movietracker.trakt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Optional;

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

    private final Logger logger = LoggerFactory.getLogger(TraktClientConfig.class);

    @Bean
    public RestClient traktMoviesRestClient(Optional<Instant> startAt, Optional<Instant> endAt, ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        return RestClient.builder()
                .baseUrl(TRAKT_BASE_URL + TRAKT_USERNAME + "/history/movies")
                .defaultHeaders(
                        httpHeaders -> {
                            httpHeaders.set("content-type", MediaType.APPLICATION_JSON_VALUE);
                            httpHeaders.set("trakt-api-version", TRAKT_API_VERSION);
                            httpHeaders.set("trakt-api-key", TRAKT_CLIENT_ID);
                        })
                .requestInterceptor(clientLoggerRequestInterceptor)
                .build();
    }
}


