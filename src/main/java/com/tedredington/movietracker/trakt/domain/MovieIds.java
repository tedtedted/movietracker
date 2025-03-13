package com.tedredington.movietracker.trakt.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieIds {
    @JsonProperty("trakt")
    private int traktId;

    @JsonProperty("imdb")
    private String imdbId;

    @JsonProperty("tmdb")
    private int tmdbId;

    public int getTraktId() {
        return traktId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public MovieIds(int traktId, String imdbId, int tmdbId) {
        this.traktId = traktId;
        this.imdbId = imdbId;
        this.tmdbId = tmdbId;
    }

    @Override
    public String toString() {
        return "MovieIds{" +
                "traktId=" + traktId +
                ", imdbId='" + imdbId + '\'' +
                ", tmdbId=" + tmdbId +
                '}';
    }
}
