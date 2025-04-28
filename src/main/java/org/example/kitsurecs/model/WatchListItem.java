package org.example.kitsurecs.model;

public class WatchListItem {

    //fields
    private final int WatchListItemId;
    private final int anime_id;
    private boolean watched;
    private boolean favorite;

    //getters
    public int getWatchListItemId() {
        return WatchListItemId;
    }

    public int getAnimeId() {
        return anime_id;
    }

    public boolean getWatched(){
        return watched;
    }

    public boolean getFavorite(){
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
    public WatchListItem(int WatchListItemId, int anime_id, boolean watched, boolean favorite){
        this.WatchListItemId = WatchListItemId;
        this.anime_id = anime_id;
        this.watched = watched;
        this.favorite = favorite;
    }
}
