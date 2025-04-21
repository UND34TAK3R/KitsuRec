//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

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

    //setters
    public void setDuration(int duration) { this.duration = duration; }
    public void setMovie_release_date(String movie_release_date) { this.movie_release_date = movie_release_date; }

    //constructor
    public Movie(int anime_id, String title, Main_Picture main_picture, String synopsis, Double mean, List<Genre> genres, String media_type, int duration, String movie_release_date) {
        super(anime_id, title, main_picture, synopsis, mean, genres, media_type);
        this.duration = duration;
        this.movie_release_date = movie_release_date;
    }
}
