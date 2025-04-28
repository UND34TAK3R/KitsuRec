package org.example.kitsurecs.model;

import java.util.List;

public class WatchList {
    private final int WatchListId;
    private final int watchListItemId;
    private final int user_id;
    private List<WatchListItem> watchListItems;

    //getters
    public int getWatchListId() {
        return WatchListId;
    }
    public int getWatchListItemId() {
        return watchListItemId;
    }
    public int getUserId() {
        return user_id;
    }
    public List<WatchListItem> getWatchListItems() {
        return watchListItems;
    }

    //setter
    public void setWatchListItems(List<WatchListItem> watchListItems) {
        this.watchListItems = watchListItems;
    }

    //constructor
    public WatchList(int watchListId, int watchListItemId, int user_id, List<WatchListItem> watchListItems) {
        this.WatchListId = watchListId;
        this.watchListItemId = watchListItemId;
        this.user_id = user_id;
        this.watchListItems = watchListItems;
    }

    public WatchList(int watchListId, int user_id, List<WatchListItem> watchListItems) {
        this.WatchListId = watchListId;
        this.user_id = user_id;
        this.watchListItems = watchListItems;

    }

    //Method
    public WatchListItem AddWatchListItem(int watchListItemId, int anime_id, boolean watched, boolean favorite) {
        WatchListItem watchListItem = new WatchListItem(watchListItemId, anime_id, watched, favorite);
        watchListItems.add(watchListItem);
        return watchListItem;
    }


}
