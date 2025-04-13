package org.example.kitsurecs.model;

public class WatchListItem {

    //fields
    private final String WatchListItemId;
    private final int anime_id;
    private boolean watched;
    private boolean favorite;

    //getters
    public String getWatchListItemId() {
        return WatchListItemId;
    }

    public int getAnimeId() {
        return anime_id;
    }

    private boolean getWatched(){
        return watched;
    }

    private boolean getFavorite(){
        return favorite;
    }

    //setters
    private void setWatched(boolean watched){
        this.watched = watched;
    }
    private void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    //constructor
    public WatchListItem(String WatchListItemId, int anime_id, boolean watched, boolean favorite){
        this.WatchListItemId = WatchListItemId;
        this.anime_id = anime_id;
        this.watched = watched;
        this.favorite = favorite;
    }
}
