package com.tedredington.movietracker.trakt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WatchlistMovieRunner implements ApplicationRunner  {

    private final WatchlistMovieService watchlistMovieService;
    private final LetterboxdCsvWriter letterboxdCsvWriter;

    private final static Logger log = LoggerFactory.getLogger(WatchlistMovieRunner.class);

    public WatchlistMovieRunner(WatchlistMovieService watchlistMovieService, LetterboxdCsvWriter letterboxdCsvWriter) {
        this.watchlistMovieService = watchlistMovieService;
        this.letterboxdCsvWriter = letterboxdCsvWriter;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<WatchlistMovie> watchlistMovieList = watchlistMovieService.getWatchlistMovies();

        letterboxdCsvWriter.writeWatchlistMoviesToCsv(watchlistMovieList);
        log.info("Watched movies written to CSV in /data directory successfully.");
    }
}
