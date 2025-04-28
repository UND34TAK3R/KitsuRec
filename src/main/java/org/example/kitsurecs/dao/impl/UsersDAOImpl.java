package org.example.kitsurecs.dao.impl;

import org.example.kitsurecs.dao.UsersDAO;
import org.example.kitsurecs.db.DbUtil;
import org.example.kitsurecs.model.Role;
import org.example.kitsurecs.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.err;

public class UsersDAOImpl implements UsersDAO {
    /**
     * Registers the user in the database
     * @param user_id the User ID
     * @param username the Username
     * @param password the Password
     * @param user_email the User email
     * @return true if successful, false is unsucessful
     */
    @Override
    public boolean registerUser(String user_id, String username, String password, String user_email) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Check if user already exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ? AND email = ?");
            checkStmt.setString(1, username);
            checkStmt.setString(2, user_email);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            // Insert if user doesn't exist yet
            if (count == 0) {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users (user_id, username, email, password, profile_picture) VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, user_id);
                stmt.setString(2, username);
                stmt.setString(3, user_email);
                stmt.setString(4, password);
                stmt.setString(5, "");
                int result = stmt.executeUpdate();
                return result > 0;
            }
            return true;
        } catch (SQLException e) {
            err.println("An error has occured when trying to register a user: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    /**
     * Logs the user onto the website using the database to check
     * @param email the user's email
     * @param password the user's password (non-hashed)
     * @return the user if successful, null if unsuccessful
     */
    @Override
    public User loginUser(String email, String password) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Check if user exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT user_id, username, email, password, profile_picture FROM Users WHERE email = ?");
            checkStmt.setString(1, email);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                String uid = rs.getString("user_id");
                String username = rs.getString("username");
                String pfp = rs.getString("profile_picture");
                String hashedPw = rs.getString("password"); // Hashed password

                // Unhash the password
                User temp = new User(uid, username, email, hashedPw, pfp, Role.user);
                if (temp.checkPassword(hashedPw)) {
                    return temp;
                } else {
                    return null;
                }
            }
            return null; // not found
        } catch (SQLException e) {
            err.println("An error has occured when trying to login user: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    /**
     * Save a user's changes
     * @param user the specified user
     * @return true if successful, false otherwise
     */
    @Override
    public boolean saveUser(User user) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Check if exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE user_id = ?");
            checkStmt.setString(1, user.getUser_id());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            PreparedStatement stmt = null;
            if (count > 0) {
                // Update user
                stmt = conn.prepareStatement("UPDATE Users SET username = ?, email = ?, password = ?, profile_picture = ?");
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.hashPassword(user.getPassword()));
                stmt.setString(4, user.getProfile_picture());
            }

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            err.println("An error has occured when saving user: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    /**
     * Deletes a specified user from the database
     * @param user the specified user
     * @return true if successful, otherwise false
     */
    @Override
    public boolean deleteUser(User user) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE user_id = ?");
            stmt.setString(1, user.getUser_id());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            err.println("An error has occured when deleting user: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    /**
     * Verifies if a specifies email exists
     * @param email the email to check
     * @return true if found, otherwise false
     */
    @Override
    public boolean emailExists(String email) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE email = ?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return true;
        } catch (SQLException e) {
            err.println("Error checking if email exists: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }
}
