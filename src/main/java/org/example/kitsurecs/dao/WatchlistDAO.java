package org.example.kitsurecs.dao;

import org.example.kitsurecs.model.WatchList;

import java.util.List;

public interface WatchlistDAO {

    /**
     * Find a watchlist from a Watchlist ID
     * @param watchListID the WatchListID
     * @return the WatchList if found or null if none found
     */
    WatchList findWatchList(int watchListID);

    /**
     * Find all watchlist
     * @return list of WatchLists
     */
    List<WatchList> findWatchLists();

    /**
     * Saves the WatchList (if changes are made or automatically creates one of none exists)
     * @param watchList the watchList to save
     * @return true if successful
     */
    boolean saveWatchList(WatchList watchList);
}