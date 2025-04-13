package org.example.kitsurecs.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User {
    //fields
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final String user_id;
    private String username;
    private String email;
    private String password;
    private String profile_picture;


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

    //setters
    public void setPassword(String newPassword) {
        password = newPassword;
    }
    public void setProfile_picture(String newProfilePicture) {
        profile_picture = newProfilePicture;
    }
    public void setEmail(String newEmail) {
        email = newEmail;
    }

    //constructor
    public User(String user_id,String username, String email, String password, String profilePicture) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile_picture = profilePicture;
    }

    //methods
    public void login(){

    }

    public void register(){

    }

    public void logout(){

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
