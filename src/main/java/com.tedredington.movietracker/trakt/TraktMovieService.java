package com.tedredington.movietracker.trakt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TraktMovieService {

    private final RestClient traktMovieRestClient;
    private final Logger logger = LoggerFactory.getLogger(TraktMovieService.class);

    public TraktMovieService(RestClient traktMovieRestClient) {
        this.traktMovieRestClient = traktMovieRestClient;
    }

    public List<WatchedMovie> getWatchedMovies() {
        List<WatchedMovie> response = traktMovieRestClient.get()
                .retrieve()
                .body(new ParameterizedTypeReference<List<WatchedMovie>>() {
                });
        logger.info(response.toString());
        return response;
    }

    public List<WatchedMovie> getWatchedMovies(Optional<Instant> startAt, Optional<Instant> endAt) {

        return traktMovieRestClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("");  // Use base URL from RestClient
                    startAt.ifPresent(start -> uriBuilder.queryParam("start_at", start.toString()));
                    endAt.ifPresent(end -> uriBuilder.queryParam("end_at", end.toString()));
                    uriBuilder.queryParam("limit", 100);
                    return uriBuilder.build();
                })
                .retrieve()
                .body(new ParameterizedTypeReference<List<WatchedMovie>>() {
                });
    }

    public List<WatchedMovie> getWatchedMoviesByMonth() {
        List<WatchedMovie> allMovies = new ArrayList<>();

        //LocalDate current = LocalDate.of(LocalDate.now().getYear(), 1, 1);  // Start from January 1 of the current year
        LocalDate current = LocalDate.of(1990, 1, 1);
        LocalDate endOfYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);  // End of the year

        while (current.isBefore(endOfYear)) {
            LocalDate startOfMonth = current.withDayOfMonth(1);
            LocalDate endOfMonth = current.withDayOfMonth(current.lengthOfMonth());

            Instant startAt = startOfMonth.atStartOfDay().toInstant(ZoneOffset.UTC);
            Instant endAt = endOfMonth.atTime(23, 59, 59).toInstant(ZoneOffset.UTC);

            // Call the API for the specific month
            List<WatchedMovie> moviesForMonth = getWatchedMovies(Optional.of(startAt), Optional.of(endAt));

            if (!moviesForMonth.isEmpty()) {
                logger.info(moviesForMonth.toString());
                allMovies.addAll(moviesForMonth);
            }

            try {
                sleepForHalfSecond();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Move to the next month
            current = current.plusMonths(1);
        }

        return allMovies;
    }

    private void sleepForHalfSecond() throws InterruptedException {
        long HALF_SECOND = 500;
        Thread.sleep(HALF_SECOND);
    }
}