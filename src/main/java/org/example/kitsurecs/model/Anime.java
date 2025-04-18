//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

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

    //setters
    public void setAnime_id(int anime_id) { this.anime_id = anime_id; }
    public void setTitle(String title) { this.title = title; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setMean(Double mean) { this.mean = mean; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }
    public void setType(String type) { this.type = type; }

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
