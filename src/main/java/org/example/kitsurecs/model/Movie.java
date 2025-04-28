//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

package org.example.kitsurecs.model;

import java.util.List;

public class Movie extends Anime {

    // Fields
    private int duration;
    private String movie_release_date;

    // Getters
    public int getDuration() {
        return duration;
    }
    public String getMovie_release_date() {
        return movie_release_date;
    }

    // Setters
    public void setDuration(int duration) { this.duration = duration; }
    public void setMovie_release_date(String movie_release_date) { this.movie_release_date = movie_release_date; }

    /**
     * Movie constructor
     * @param anime_id the anime movie ID
     * @param title the movie title
     * @param main_picture the main picture (medium/large)
     * @param synopsis the movie synopsis
     * @param mean the movie mean
     * @param genres the movie genre(s)
     * @param media_type the media type
     * @param duration the movie duration
     * @param movie_release_date the release date
     */
    public Movie(int anime_id, String title, Main_Picture main_picture, String synopsis, Double mean, List<Genre> genres, String media_type, int duration, String movie_release_date) {
        super(anime_id, title, main_picture, synopsis, mean, genres, media_type);
        this.duration = duration;
        this.movie_release_date = movie_release_date;
    }
}
