//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

package org.example.kitsurecs.model;

import java.util.List;

public class Show extends Anime {

    // Fields
    private String start_date;
    private String end_date;
    private int num_episodes;
    private int average_episode_duration;

    // Getters
    public String getStart_date() {
        return start_date;
    }
    public String getEnd_date() {
        return end_date;
    }
    public int getNum_episodes() {
        return num_episodes;
    }
    public int getAverage_episode_duration() {
        return average_episode_duration;
    }

    // Setters
    public void setStart_date(String start_date) { this.start_date = start_date; }
    public void setEnd_date(String end_date) { this.end_date = end_date; }
    public void setNum_episodes(int num_episodes) { this.num_episodes = num_episodes; }
    public void setAverage_episode_duration(int average_episode_duration) { this.average_episode_duration = average_episode_duration; }

    /**
     * Show (anime) constructor
     * @param anime_id the anime ID
     * @param title the anime title
     * @param main_picture the main picture (medium/large)
     * @param synopsis the anime synopsis
     * @param mean the anime mean
     * @param genres the anime genre(s)
     * @param media_type the media type (OVA, movie, etc)
     * @param start_date the anime start date
     * @param end_date the anime end date
     * @param num_episodes the number of episodes
     * @param average_episode_duration the average duration (usually 21-23 min for anime)
     */
    public Show(int anime_id, String title, Main_Picture main_picture, String synopsis, Double mean, List<Genre> genres, String media_type, String start_date, String end_date, int num_episodes, int average_episode_duration) {
        super(anime_id, title, main_picture, synopsis, mean, genres, media_type);
        this.start_date = start_date;
        this.end_date = end_date;
        this.num_episodes = num_episodes;
        this.average_episode_duration = average_episode_duration;
    }
}
