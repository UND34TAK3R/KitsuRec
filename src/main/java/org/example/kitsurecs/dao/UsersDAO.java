package org.example.kitsurecs.dao;

import org.example.kitsurecs.model.User;

public interface UsersDAO {

    /**
     * Register a user to the website
     * @param user_id the User ID
     * @param username the Username
     * @param password the Password
     * @param user_email the User email
     * @return true if successful
     */
    boolean registerUser(int user_id, String username, String password, String user_email);

    /**
     * Login a user to the website
     * @param username the username
     * @param password the password (non-hashed)
     * @return true if successful
     */
    boolean loginUser(String username, String password);

    /**
     * Saves the user's profile changes
     * @param users the User
     * @return true if successful
     */
    boolean saveUser(User users);
}