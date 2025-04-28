package org.example.kitsurecs.dao.impl;

import org.example.kitsurecs.dao.WatchlistDAO;
import org.example.kitsurecs.db.DbUtil;
import org.example.kitsurecs.model.WatchList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.lang.System.err;

public class WatchlistDAOImpl implements WatchlistDAO {

    @Override
    public WatchList findWatchList(int watchListID) {
        WatchList searchList = null;
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM WatchList WHERE watchlist_id = ?");
            stmt.setInt(1, watchListID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int watchlistItemID = rs.getInt("watchlist_itemid");
                int userID = rs.getInt("user_id");

                // Need to do a loop to iterate through the watchlist itemIDs and put them all in a list
                for ()

            }
        } catch (Exception e) {
            err.println("Error finding watchlist: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }



}