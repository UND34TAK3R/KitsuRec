package org.example.kitsurecs.model;

import java.util.List;

public class WatchList {
    private final int WatchListId;
    private final int user_id;
    private List<WatchListItem> watchListItems;

    // Getters
    public int getWatchListId() {
        return WatchListId;
    }
    public int getUserId() {
        return user_id;
    }
    public List<WatchListItem> getWatchListItems() {
        return watchListItems;
    }

    // Setters
    public void setWatchListItems(List<WatchListItem> watchListItems) {
        this.watchListItems = watchListItems;
    }

    /**
     * Watchlist constructor
     * @param watchListId the watchlist ID
     * @param user_id the user ID
     * @param watchListItems the watchlist items (list)
     */
    public WatchList(int watchListId, int user_id, List<WatchListItem> watchListItems) {
        this.WatchListId = watchListId;
        this.user_id = user_id;
        this.watchListItems = watchListItems;
    }

    // Methods
    /**
     * Adds an anime to the user's watchlist item
     * @param watchListItemId the watch list item ID
     * @param anime_id the anime ID
     * @param watched watched bool
     * @param favorite favourite bool
     * @return WatchListItem
     */
    public WatchListItem AddWatchListItem(int watchListItemId, int anime_id, boolean watched, boolean favorite) {
        WatchListItem watchListItem = new WatchListItem(watchListItemId, anime_id, watched, favorite);
        watchListItems.add(watchListItem);
        return watchListItem;
    }


}
