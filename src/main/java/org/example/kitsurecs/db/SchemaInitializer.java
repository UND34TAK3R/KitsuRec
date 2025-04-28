package org.example.kitsurecs.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.out;
import static java.lang.System.err;

public class SchemaInitializer {
    public static void initializeSchema() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Table creation
            Statement stmt = conn.createStatement();

            // Create User table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Users (" +
                            "  user_id INT PRIMARY KEY AUTO_INCREMENT,  " +
                            "  username VARCHAR(20) NOT NULL,  " +
                            "  email VARCHAR2(30) UNIQUE NOT NULL,  " +
                            "  password VARCHAR2(50) NOT NULL,  " +
                            "  profile_picture VARCHAR2(100),   " +
                            "  created_datetime CURRENT_DATETIME,  " +
                            "  modified_datetime CURRENT_DATETIME" +
                            ")"
            );
            out.println("Users table created.");

            // Create Watchlist Table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Watchlist (" +
                        "  watchlist_id INT PRIMARY KEY AUTO_INCREMENT,  " +
                        "  watchlist_itemid INT,  " +
                        "  user_id INT NOT NULL,  " +
                        "  created_at CURRENT_DATETIME,  " +
                        "  FOREIGN KEY (user_id) REFERENCES Users(user_id)" +
                        ")"
            );
            out.println("Watchlist table created.");

            // Create Watchlist item table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS WatchlistItem (" +
                        "   watchlist_itemid INT PRIMARY KEY AUTO_INCREMENT,"  +
                        "   watchlist_id INT,   " +
                        "   anime_id INT,   " +
                        "   favourite BOOLEAN DEFAULT FALSE,   " +
                        "   watched BOOLEAN DEFAULT FALSE,   " +
                        "   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   " +
                        "   FOREIGN KEY (watchlist_id) REFERENCES Watchlist(watchlist_id)"
            );
            out.println("Watchlist item table created.");

        } catch (SQLException e) {
            err.println("Error setting up the database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }
}
