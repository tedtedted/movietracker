package com.tedredington.movietracker.trakt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class WatchedMoviesRunner implements ApplicationRunner {

    private final TraktMovieService traktService;
    private final LetterboxdCsvWriter letterboxdCsvWriter;

    private final static Logger log = LoggerFactory.getLogger(WatchedMoviesRunner.class);

    public WatchedMoviesRunner(TraktMovieService traktService, LetterboxdCsvWriter letterboxdCsvWriter) {
        this.traktService = traktService;
        this.letterboxdCsvWriter = letterboxdCsvWriter;
    }

    @Override
    public void run(ApplicationArguments args) {

        List<WatchedMovie> watchedMovies = traktService.getWatchedMovies();

        try {
            letterboxdCsvWriter.writeWatchedMoviesToCsv(watchedMovies);
            log.info("Watched movies written to CSV in /data directory successfully.");
        } catch (IOException e) {
            log.error("Error writing to CSV: {}", e.getMessage());
        }
    }
}
