package org.example.kitsurecs.model;

import java.util.List;

public class Anime {

    //fields
    private int anime_id;
    private String title;
    private Main_Picture main_picture;
    private String synopsis;
    private Double mean;
    private List<Genre> genres;
    private String type;
    private String rating;


    //getters
    public int getAnime_id() {
        return anime_id;
    }
    public String getTitle() {
        return title;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public Double getMean() {
        return mean;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public String getType() {
        return type;
    }
    public String getRating() {
        return rating;
    }

    //constructor
    public Anime(int anime_id, String title, String synopsis, Double mean, List<Genre> genres, String type, String rating) {
        this.anime_id = anime_id;
        this.title = title;
        this.synopsis = synopsis;
        this.mean = mean;
        this.genres = genres;
        this.type = type;
        this.rating = rating;
    }
}
