package com.tedredington.movietracker.trakt.domain;


public class Movie {
    private String title;
    private int year;
    private MovieIds ids; // Assuming a single set of IDs per movie

    public Movie(String title, int year, MovieIds ids) {
        this.title = title;
        this.year = year;
        this.ids = ids;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public MovieIds getIds() {
        return ids;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", ids=" + ids +
                '}';
    }
}