package org.example.kitsurecs.model;

//History Revision:
// NAME                     DATE                COMMENTS
// Derrick Mangari     2025/04/15       Created Admin Class with constructor and started methods (not finished need to implement DAO and Update User Info)

public class Admin extends User {

    //constructor
    public Admin(int user_id, String username, String email, String password, String profilePicture, Role role) {
        super(user_id, username, email, password, profilePicture, role);
    }

    //set role
    public void changeRole(Role newRole, User user) {
        user.setRole(newRole);
    }

    //method to delete a user
    public void deleteUser(User user) {
        try {
            int user_id = user.getUser_id();
            //implement UserDAO DeleteUserById here
        }catch (Exception e) {
            System.out.println("Unable to delete user: " + e.getMessage());
        }
    }

    //method to update User Info
    public  void UpdateUserInfo(User user) {
        try {
            int user_id = user.getUser_id();
            //Still need to think how to implement this
            //Need DAO UpdateUser to work on it
        }catch (Exception e) {
            System.out.println("Unable to update user: " + e.getMessage());
        }
    }

    //method to add Users
    public void AddUser(String username, String email, String password, String profilePicture, Role role){
        try{
            String hashPassword = hashPassword(password);
            User newUser = new User(0, username, email, hashPassword, profilePicture, role);
            //need DAO create User
            //takes all info and creates User
            System.out.println("User created successfully!");
        }catch(Exception e){
            System.out.println("Failed to create user: " + e);
        }
    }
}