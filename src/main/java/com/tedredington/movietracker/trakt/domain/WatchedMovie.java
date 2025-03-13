package com.tedredington.movietracker.trakt.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class WatchedMovie {

    private Long id;

    @JsonProperty("watched_at")
    private Instant watchedAt;

    private Movie movie;

    public WatchedMovie(Long id, Instant watchedAt, Movie movie) {
        this.id = id;
        this.watchedAt = watchedAt;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public Instant getWatchedAt() {
        return watchedAt;
    }

    public Movie getMovie() {
        return movie;
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