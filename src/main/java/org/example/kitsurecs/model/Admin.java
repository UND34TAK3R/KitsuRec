package org.example.kitsurecs.model;

//History Revision:
// NAME                     DATE                COMMENTS
// Derrick Mangari     2025/04/15       Created Admin Class with constructor and started methods (not finished need to implement DAO and Update User Info)

public class Admin extends User {

    // Constructors

    /**
     * Admin Constructor
     * @param user_id
     * @param username
     * @param email
     * @param password
     * @param profilePicture
     * @param role
     */
    public Admin(String user_id, String username, String email, String password, String profilePicture, Role role) {
        super(user_id, username, email, password, profilePicture, role);
    }

    /**
     * Changes the role of a specified user
     * @param newRole role to change to
     * @param user to change role
     */
    public void changeRole(Role newRole, User user) {
        user.setRole(newRole);
    }

    /**
     * Delete a user
     * @param user user to delete
     */
    public void deleteUser(User user) {
        try {
            String user_id = user.getUser_id();
            //implement UserDAO DeleteUserById here
        }catch (Exception e) {
            System.out.println("Unable to delete user: " + e.getMessage());
        }
    }

    /**
     * Updates a specified user's info
     * @param user user to update
     */
    public  void UpdateUserInfo(User user) {
        try {
            String user_id = user.getUser_id();
            //Still need to think how to implement this
            //Need DAO UpdateUser to work on it
        }catch (Exception e) {
            System.out.println("Unable to update user: " + e.getMessage());
        }
    }

    /**
     * Add a user manually
     * @param username username to use
     * @param email email to use
     * @param password password to use
     * @param profilePicture profile picture to use
     * @param role role to use
     */
    public void AddUser(String username, String email, String password, String profilePicture, Role role){
        try{
            String hashPassword = hashPassword(password);
            User newUser = new User("", username, email, hashPassword, profilePicture, role);
            //need DAO create User
            //takes all info and creates User
            System.out.println("User created successfully!");
        }catch(Exception e){
            System.out.println("Failed to create user: " + e);
        }
    }
}