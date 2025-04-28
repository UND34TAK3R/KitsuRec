package org.example.kitsurecs.model;

public class WatchListItem {

    // Fields
    private final int WatchListItemId;
    private final int anime_id;
    private boolean watched;
    private boolean favorite;

    // Getters
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

    // Setters
    private void setWatched(boolean watched){
        this.watched = watched;
    }
    private void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    /**
     * WatchListItem constructor
     * @param WatchListItemId the watchlist item ID
     * @param anime_id the anime ID
     * @param watched bool watched
     * @param favorite bool favourite
     */
    public WatchListItem(int WatchListItemId, int anime_id, boolean watched, boolean favorite){
        this.WatchListItemId = WatchListItemId;
        this.anime_id = anime_id;
        this.watched = watched;
        this.favorite = favorite;
    }
}
