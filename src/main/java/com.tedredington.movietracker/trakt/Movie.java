package com.tedredington.movietracker.trakt;

public record Movie(
        String title,
        int year,
        MovieIds ids
)
{}
