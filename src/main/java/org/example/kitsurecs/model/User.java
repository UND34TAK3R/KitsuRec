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
    public String hashPassword(String password){
        return encoder.encode(password);
    }

    public boolean checkPassword(String password){
        return encoder.matches(password, this.password);
    }
}
