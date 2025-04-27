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
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM WatchList WHERE watchlist_itemid = ?");
            stmt.setInt(1, watchListID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Fill rest once everything is figured out
            }
        } catch (Exception e) {
            err.println("Error finding watchlist: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }


}