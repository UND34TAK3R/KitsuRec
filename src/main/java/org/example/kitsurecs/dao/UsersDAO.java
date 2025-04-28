package org.example.kitsurecs.dao;

import org.example.kitsurecs.model.User;

import java.sql.SQLException;

public interface UsersDAO {

    /**
     * Register a user to the website
     * @param user_id the User ID
     * @param username the Username
     * @param password the Password
     * @param user_email the User email
     * @return true if successful
     */
    boolean registerUser(String user_id, String username, String password, String user_email);

    /**
     * Login a user to the website
     * @param email the username
     * @param password the password (non-hashed)
     * @return the User
     */
    User loginUser(String email, String password);

    /**
     * Saves the user's profile changes
     * @param users the User
     * @return true if successful
     */
    boolean saveUser(User users);

    /**
     * Deletes a user from the website
     * @param users the User
     * @return true if successful
     */
    boolean deleteUser(User users);


    /**
     * Checks if the email exists (helps for registration)
     * @param email the user's email
     * @return true if exists
     */
    boolean emailExists(String email);
}