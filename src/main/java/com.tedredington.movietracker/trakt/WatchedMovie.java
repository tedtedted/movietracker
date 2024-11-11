package com.tedredington.movietracker.trakt;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;

public class WatchedMovie {
    private Long id;

    @JsonProperty("watched_at")
    private Instant watchedAt;

    private Movie movie;

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Instant getWatchedAt() {
        return watchedAt;
    }

    public WatchedMovie(Long id, Instant watchedAt, Movie movie) {
        this.id = id;
        this.watchedAt = watchedAt;
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "WatchedMovie{" +
                "id=" + id +
                ", watchedAt=" + watchedAt +
                ", movie=" + movie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchedMovie that = (WatchedMovie) o;
        return Objects.equals(id, that.id) && Objects.equals(watchedAt, that.watchedAt) && Objects.equals(movie, that.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, watchedAt, movie);
    }
}
