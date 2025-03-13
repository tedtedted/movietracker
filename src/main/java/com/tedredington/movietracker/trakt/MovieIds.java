package com.tedredington.movietracker.trakt;

import com.fasterxml.jackson.annotation.JsonProperty;

record MovieIds(
        @JsonProperty("trakt") int traktId,
        @JsonProperty("imdb") String  imdbId,
        @JsonProperty("tmdb") int tmdbId
)
{}
