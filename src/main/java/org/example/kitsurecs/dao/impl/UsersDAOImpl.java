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
    @Override
    public boolean registerUser(int user_id, String username, String password, String user_email) {
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
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users (user_id, username, email, password) VALUES (?, ?, ?, ?)");
                stmt.setInt(1, user_id);
                stmt.setString(2, username);
                stmt.setString(3, user_email);
                stmt.setString(4, password);
                int result = stmt.executeUpdate();
                return result > 0;
            }
            return true;
        } catch (SQLException e) {
            err.println("An error has occured when trying to register a student: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override
    public User loginUser(String email, String password) {
        Connection conn = null;
        User users = null;
        try {
            conn = DbUtil.getConnection();

            // Check if user exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE email = ?");
            checkStmt.setString(1, email);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt("user_id");
                String username = rs.getString("username");
                String pfp = rs.getString("profilePicture");
                Role role = Role.valueOf(rs.getString("role"));
                return new User(uid, username, email, password, pfp, role);
            }
            return null;
        } catch (SQLException e) {
            err.println("An error has occured when trying to login user: " + e.getMessage());
            return null;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override
    public boolean saveUser(User user) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // Check if exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE user_id = ?");
            checkStmt.setInt(1, user.getUser_id());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            PreparedStatement stmt = null;
            if (count > 0) {
                // Update user
                stmt = conn.prepareStatement("UPDATE Users SET username = ?, email = ?, password = ?, profilepicture = ?");
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

    @Override
    public boolean deleteUser(User user) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Users WHERE user_id = ?");
            stmt.setInt(1, user.getUser_id());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            err.println("An error has occured when deleting user: " + e.getMessage());
            return false;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    @Override
    public void registerUserToDatabase(int user_id, String username, String password, String user_email) {
        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users (username, email, password) VALUES (?, ?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, user_email);
            stmt.setString(3, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            err.println("Error registering user to the database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

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
