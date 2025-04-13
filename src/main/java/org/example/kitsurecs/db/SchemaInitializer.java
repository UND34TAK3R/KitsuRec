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

            // Create Watchlist Table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Watchlist (" +
                        "  watchlist_itemid INT PRIMARY KEY,  " +
                        "  user_id INT UNIQUE,  " +
                        "  anime_id INT UNIQUE,  " +
                        "  favourite BOOLEAN, " +
                        " created_at CURRENT_DATETIME" +
                        ")"
            );
            out.println("Watchlist table created.");

            // Create User table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Users (" +
                        "  user_id INT PRIMARY KEY,  " +
                        "  email VARCHAR2(30) UNIQUE NOT NULL,  " +
                        "  password VARCHAR2(50) NOT NULL,  " +
                        "  created_datetime CURRENT_DATETIME,  " +
                        "  modified_datetime CURRENT_DATETIME" +
                        ")"
            );
        } catch (SQLException e) {
            err.println("Error setting up the database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }
}
