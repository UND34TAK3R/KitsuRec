package org.example.kitsurecs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.err;
import static java.lang.System.out;

public class DbUtil {
    // JDBC URL for whichever database
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/KitsuRecs"; // keep this for testing purposes
    //private static final String JDBC_URL = "jdbc:h2:~/KitsuRecs;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // Static driver for JDBC driver
    static {
        try {
            Class.forName("org.h2.Driver");
            out.println("JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            out.println("JDBC driver not found.");
            e.printStackTrace();
        }
    }

    /**
     * Get a database connection
     * @return a new database connection
     * @throws SQLException if it fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    /**
     * Close a connection quietly (without throwing exceptions)
     * @param conn the connection to close
     */
    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                err.println("Failed to close connection: " + e.getMessage());
            }
        }
    }

}
