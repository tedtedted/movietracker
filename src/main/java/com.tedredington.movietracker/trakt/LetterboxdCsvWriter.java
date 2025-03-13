package com.tedredington.movietracker.trakt;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class LetterboxdCsvWriter {

    private static final Logger logger = LoggerFactory.getLogger(LetterboxdCsvWriter.class);

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            // on a whim, decided against my better judgment to go with local time and not UTC
            .withZone(ZoneId.systemDefault());
            // .withZone(ZoneId.of("UTC"));

    public void writeWatchedMoviesToCsv(List<WatchedMovie> watchedMovies) throws IOException {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String date = DATE_FORMATTER.format(Instant.now());
        String filePath = "data/watched_movies_" + date + "_" + timestamp + ".csv";

        // Ensure the /data directory exists
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }


        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeNext(new String[]{"Title", "Year", "tmdbID", "imdbID", "WatchedDate"});
            for (WatchedMovie movie : watchedMovies) {
                writer.writeNext(new String[]{
                        escapeField(movie.getMovie().title()),
                        String.valueOf(movie.getMovie().year()),
                        String.valueOf(movie.getMovie().ids().tmdbId()),
                        String.valueOf(movie.getMovie().ids().imdbId()),
                        DATE_FORMATTER.format(movie.getWatchedAt())
                });
            }
            logger.info("File written: {}", filePath);
        }
    }

    private String escapeField(String title) {
        // Escape single quotes by adding a backslash in front
        return title.replace("'", "\\'");
    }

    public void writeWatchlistMoviesToCsv(List<WatchlistMovie> watchlistMovies) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String date = DATE_FORMATTER.format(Instant.now());
        String filePath = "data/watchlist_movies_" + date + "_" + timestamp + ".csv";

        // Ensure the /data directory exists
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }


        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeNext(new String[]{"Title", "Year", "tmdbID", "imdbID"});
            for (WatchlistMovie movie : watchlistMovies) {
                writer.writeNext(new String[]{
                        escapeField(movie.getMovie().title()),
                        String.valueOf(movie.getMovie().year()),
                        String.valueOf(movie.getMovie().ids().tmdbId()),
                        String.valueOf(movie.getMovie().ids().imdbId()),
                });
            }
            logger.info("File written: {}", filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
