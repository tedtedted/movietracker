package com.tedredington.movietracker.trakt;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record WatchedMovie(
        Long id,
        @JsonProperty("watched_at") Instant watchedAt,
        Movie movie
) {}