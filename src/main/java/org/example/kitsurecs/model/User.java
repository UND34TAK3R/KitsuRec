package org.example.kitsurecs.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Console;


//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari  2025/04/15      Added new field role and made remaining methods without session token and DAOs(made comments on steps to take)
public class User {
    //fields
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final String user_id;
    private String username;
    private String email;
    private String password;
    private String profile_picture;
    private Role role;


    //getters
    public String getUser_id() {
        return user_id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getProfile_picture() {
        return profile_picture;
    }
    public String getEmail() {
        return email;
    }
    public Role getRole() {return role;}

    //setters
    public void setUsername(String newUsername) {username=newUsername;}
    public void setPassword(String newPassword) {
        password = newPassword;
    }
    public void setProfile_picture(String newProfilePicture) {
        profile_picture = newProfilePicture;
    }
    public void setEmail(String newEmail) {
        email = newEmail;
    }
    public void setRole(Role newRole) {role = newRole;}

    //constructor
    public User(String user_id,String username, String email, String password, String profilePicture, Role role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile_picture = profilePicture;
        this.role = role;
    }

    //methods

    //method to login
    public void login(String inputEmail, String inputPassword){
        //send inputEmail to DAO
        //DAO returns password
        //if password matches inputPassword
        //create session token(logged in)
    }

    //methods to logout
    public void logout(){
        //delete/remove session token
    }


    //method to register
    public void register(String username, String email, String password, String profilePicture){
        try{
            String hashPassword = hashPassword(password);
            role = Role.user;
            User newUser = new User(null, username, email, hashPassword, profilePicture, role);
            //need DAO create User
            //takes all info and creates User
            System.out.println("User created successfully!");
        }catch(Exception e){
            System.out.println("Failed to register user: " + e);
        }


    }
    public void changeEmail(String email){
        setEmail(email);
    }

    public void changePassword(String newPassword){
        setPassword(newPassword);
    }

    public void changeProfilePicture(String newProfilePicture){
        setProfile_picture(newProfilePicture);
    }
    //To confirm
    public String hashPassword(String password){
        return encoder.encode(password);
    }

    public boolean checkPassword(String password){
        return encoder.matches(password, this.password);
    }
}
