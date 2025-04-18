//Revision History:
//      NAME                DATE                        COMMENTS
//      Derrick Mangari     2025/04/18              Added Setters

package org.example.kitsurecs.model;

import java.util.List;

public class Show extends Anime {

    //fields
    private String start_date;
    private String end_date;
    private int num_episodes;
    private int average_episode_duration;

    //getters
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

    //setters
    public void setStart_date(String start_date) { this.start_date = start_date; }
    public void setEnd_date(String end_date) { this.end_date = end_date; }
    public void setNum_episodes(int num_episodes) { this.num_episodes = num_episodes; }
    public void setAverage_episode_duration(int average_episode_duration) { this.average_episode_duration = average_episode_duration; }

    //constructor
    public Show(int anime_id, String title, String synopsis, Double mean, List<Genre> genres, String type, String rating, String start_date, String end_date, int num_episodes, int average_episode_duration) {
        super(anime_id, title, synopsis, mean, genres, type, rating);
        this.start_date = start_date;
        this.end_date = end_date;
        this.num_episodes = num_episodes;
        this.average_episode_duration = average_episode_duration;
    }

}
