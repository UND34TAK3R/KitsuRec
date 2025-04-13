package org.example.kitsurecs.model;

import java.util.List;

public class WatchList {
    private final String WatchListId;
    private final String watchListItemId;
    private final String user_id;
    private List<WatchListItem> watchListItems;

    //getters
    public String getWatchListId() {
        return WatchListId;
    }
    public String getWatchListItemId() {
        return watchListItemId;
    }
    public String getUserId() {
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
    public WatchList(String watchListId, String watchListItemId, String user_id, List<WatchListItem> watchListItems) {
        this.WatchListId = watchListId;
        this.watchListItemId = watchListItemId;
        this.user_id = user_id;
        this.watchListItems = watchListItems;
    }

    //Method
    public WatchListItem AddWatchListItem(String watchListItemId, int anime_id, boolean watched, boolean favorite) {
        WatchListItem watchListItem = new WatchListItem(watchListItemId, anime_id, watched, favorite);
        watchListItems.add(watchListItem);
        return watchListItem;
    }


}
