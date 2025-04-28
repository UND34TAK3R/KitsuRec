//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

package org.example.kitsurecs.model;

import java.util.List;

public class Anime {

    // Fields
    private int anime_id;
    private String title;
    private Main_Picture main_picture;
    private String synopsis;
    private Double mean;
    private List<Genre> genres;
    private String media_type;


    // Getters
    public int getAnime_id() { return anime_id; }
    public String getTitle() {
        return title;
    }
    public Main_Picture getMain_picture() {return main_picture; }
    public String getSynopsis() {
        return synopsis;
    }
    public Double getMean() {
        return mean;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public String getMediaType() {
        return media_type;
    }

    // Setters
    public void setAnime_id(int anime_id) { this.anime_id = anime_id; }
    public void setTitle(String title) { this.title = title; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public void setMain_picture(Main_Picture main_picture) { this.main_picture = main_picture; }
    public void setMean(Double mean) { this.mean = mean; }
    public void setGenres(List<Genre> genres) { this.genres = genres; }
    public void setMediaType(String media_type) { this.media_type = media_type; }

    /**
     * Anime constructor
     * @param anime_id the anime_id
     * @param title the title
     * @param main_picture the main_picture
     * @param synopsis the synopsis
     * @param mean the mean
     * @param genres the genre
     * @param media_type media type (OVA, Movie)
     */
    public Anime(int anime_id, String title, Main_Picture main_picture, String synopsis, Double mean, List<Genre> genres, String media_type) {
        this.anime_id = anime_id;
        this.title = title;
        this.main_picture = main_picture;
        this.synopsis = synopsis;
        this.mean = mean;
        this.genres = genres;
        this.media_type = media_type;
    }
}
