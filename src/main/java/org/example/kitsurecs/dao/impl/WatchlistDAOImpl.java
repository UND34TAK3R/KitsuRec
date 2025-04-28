package org.example.kitsurecs.dao.impl;

import org.example.kitsurecs.dao.WatchlistDAO;
import org.example.kitsurecs.db.DbUtil;
import org.example.kitsurecs.model.WatchList;
import org.example.kitsurecs.model.WatchListItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.err;

public class WatchlistDAOImpl implements WatchlistDAO {

    @Override
    public WatchList findWatchList(int watchListID) {
        WatchList searchList = null;
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Watchlist WHERE watchlist_id = ?");
            stmt.setInt(1, watchListID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("user_id");

                PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM WatchlistItem WHERE watchlist_id = ?");
                prepStmt.setInt(1, watchListID);
                ResultSet rs2 = prepStmt.executeQuery();
                List<WatchListItem> items = new ArrayList<WatchListItem>();
                while (rs2.next()) {
                    int watchlistItemID = rs2.getInt("watchlist_itemid");
                    int animeID = rs2.getInt("anime_id");
                    boolean favourite = rs2.getBoolean("favourite");
                    boolean watched = rs2.getBoolean("watched");

                    WatchListItem item = new WatchListItem(watchlistItemID, animeID, favourite, watched);
                    items.add(item);

                    searchList = new WatchList(watchListID, userID, items);
                }
            }
        } catch (SQLException e) {
            err.println("Error finding watchlist: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return searchList;
    }

    @Override
    public List<WatchList> findAllWatchLists() {
        Connection conn = null;
        List<WatchList> watchLists = new ArrayList<>();

        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Watchlist");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int watchlistID = rs.getInt("watchlist_id");
                int userID = rs.getInt("user_id");
                PreparedStatement itemStmt = conn.prepareStatement("SELECT * WatchlistItem WHERE watchlist_itemid = ?");
                itemStmt.setInt(1, watchlistID);
                ResultSet itemRs = itemStmt.executeQuery();
                List<WatchListItem> items = new ArrayList<>();

                while (itemRs.next()) {
                    int watchlistItemID = itemRs.getInt("watchlist_itemid");
                    int animeID = itemRs.getInt("anime_id");
                    boolean favourite = itemRs.getBoolean("favourite");
                    boolean watched = itemRs.getBoolean("watched");
                    WatchListItem item = new WatchListItem(watchlistID, animeID, favourite, watched);
                    items.add(item);
                }
                WatchList watchList = new WatchList(watchlistID, userID, items);
                watchLists.add(watchList);
            }
        } catch (SQLException e) {
            err.println("Error finding watchlists: " + e.getMessage());
            e.printStackTrace();
            return watchLists;
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return watchLists;
    }

    @Override
    public boolean saveWatchList(WatchList watchList) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Watchlist WHERE watchlist_id = ?");
            stmt.setInt(1, watchList.getWatchListId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                // Insert watchlist
                PreparedStatement watchlistStmt = conn.prepareStatement("INSERT INTO Watchlist (watchlist_id, user_id) VALUES (?, ?)");
                watchlistStmt.setInt(1, watchList.getWatchListId());
                watchlistStmt.setInt(2, watchList.getUserId());
                watchlistStmt.executeUpdate();
            }

            // Insert all watchlistitems from watchlistID
            for (WatchListItem item : watchList.getWatchListItems()) {
                PreparedStatement itemStmt = conn.prepareStatement("INSERT INTO WatchlistItem (watchlist_itemid, watchlist_id, anime_id, favourite, watched) " +
                        "VALUES (?, ?, ?, ?, ?)"
                );
                itemStmt.setInt(1, item.getWatchListItemId());
                itemStmt.setInt(2, watchList.getWatchListId());
                itemStmt.setInt(3, item.getAnimeId());
                itemStmt.setBoolean(4, item.getFavorite());
                itemStmt.setBoolean(5, item.getWatched());
                itemStmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            err.println("Error saving watchlist: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }



}
