package org.example.kitsurecs.model;

import java.util.List;

public class Movie extends Anime {
    //fields
    private int duration;
    private String movie_release_date;

    //getters
    public int getDuration() {
        return duration;
    }
    public String getMovie_release_date() {
        return movie_release_date;
    }

    //constructor
    public Movie(int anime_id, String title, String synopsis, Double mean, List<Genre> genres, String type, String rating, int duration, String movie_release_date) {
        super(anime_id, title, synopsis, mean, genres, type, rating);
        this.duration = duration;
        this.movie_release_date = movie_release_date;
    }
}
