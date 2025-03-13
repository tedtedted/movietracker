package com.tedredington.movietracker.trakt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class WatchlistMovie {

    private Integer rank;
    private Long id;
    @JsonProperty("listed_at")
    private Instant listedAt;
    private String type;
    private Movie movie;

    public WatchlistMovie(Integer rank, Long id, Instant listedAt, String type, Movie movie) {
        this.rank = rank;
        this.id = id;
        this.listedAt = listedAt;
        this.type = type;
        this.movie = movie;
    }


    public Integer getRank() {
        return rank;
    }

    public Long getId() {
        return id;
    }

    public Instant getListedAt() {
        return listedAt;
    }

    public String getType() {
        return type;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "WatchlistMovie{" +
                "rank=" + rank +
                ", id=" + id +
                ", listedAt=" + listedAt +
                ", type='" + type + '\'' +
                ", movie=" + movie +
                '}';
    }
}
