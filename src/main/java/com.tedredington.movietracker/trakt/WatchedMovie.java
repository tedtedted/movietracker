package com.tedredington.movietracker.trakt;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;

public class WatchedMovie {
    private Long id;

    @JsonProperty("watched_at")
    private Instant watchedAt;

    private final Movie movie;

    public WatchedMovie(Long id, Instant watchedAt, Movie movie) {
        this.id = id;
        this.watchedAt = watchedAt;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Instant getWatchedAt() {
        return watchedAt;
    }

    @Override
    public String toString() {
        return "WatchedMovie{" +
                "id=" + id +
                ", watchedAt=" + watchedAt +
                ", movie=" + movie +
                '}';
    }
}
