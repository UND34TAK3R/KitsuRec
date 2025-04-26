//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/22      Created Token Manager
// Derrick Mangari      2025/04/23      Added comments
// Derrick Mangari      2025/04/26      Added refresh token functionality

package org.example.kitsurecs.auth;

import org.example.kitsurecs.config.MalApiConfig;
import org.example.kitsurecs.util.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TokenManager {

    //fields
    private String accessToken;
    private String refreshToken;
    private String codeVerifier;
    private long expiresAt; // timestamp when the token expires

    //constructor
    public TokenManager() {
        // Initialize with empty token
    }

    //set a code verifier
    public void setCodeVerifier(String codeVerifier) {
        this.codeVerifier = codeVerifier;
    }

    //checks if able to fetch the access token
    public boolean fetchAccessToken(String authorizationCode, String codeVerifier) {
        try {
            //setting up the url to hit the MAL Server and connect the API
            URL url = new URL(MalApiConfig.TOKEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //the url
            String params = "grant_type=authorization_code"
                    + "&client_id=" + MalApiConfig.CLIENT_ID
                    + "&client_secret=" + MalApiConfig.CLIENT_SECRET
                    + "&code=" + authorizationCode
                    + "&redirect_uri=" + URLEncoder.encode(MalApiConfig.REDIRECT_URI, StandardCharsets.UTF_8)
                    + "&code_verifier=" + codeVerifier;

            try(OutputStream os = connection.getOutputStream()) {
                os.write(params.getBytes());
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response to extract tokens
                String responseStr = response.toString();
                this.accessToken = JsonParser.extractToken(responseStr);

                // Extract refresh token and expiration time
                this.refreshToken = JsonParser.extractRefreshToken(responseStr);
                int expiresIn = JsonParser.extractExpiresIn(responseStr);
                this.expiresAt = System.currentTimeMillis() + (expiresIn * 1000);

                return true;
            } else {
                //send error if cant get token
                System.err.println("Error getting token: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Refreshes the access token using the stored refresh token
     * @param refreshToken The refresh token to use
     * @return true if the token was successfully refreshed, false otherwise
     */
    public boolean refreshAccessToken(String refreshToken) {
        try {
            URL url = new URL(MalApiConfig.TOKEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "grant_type=refresh_token"
                    + "&client_id=" + MalApiConfig.CLIENT_ID
                    + "&client_secret=" + MalApiConfig.CLIENT_SECRET
                    + "&refresh_token=" + refreshToken;

            try(OutputStream os = connection.getOutputStream()) {
                os.write(params.getBytes());
            }

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                String responseStr = response.toString();
                this.accessToken = JsonParser.extractToken(responseStr);

                // MAL might provide a new refresh token
                String newRefreshToken = JsonParser.extractRefreshToken(responseStr);
                if (newRefreshToken != null && !newRefreshToken.isEmpty()) {
                    this.refreshToken = newRefreshToken;
                } else {
                    this.refreshToken = refreshToken; // Keep using the existing one
                }

                // Update expiration time
                int expiresIn = JsonParser.extractExpiresIn(responseStr);
                this.expiresAt = System.currentTimeMillis() + (expiresIn * 1000);

                return true;
            } else {
                System.err.println("Error refreshing token: " + connection.getResponseCode() + " " + connection.getResponseMessage());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if the current access token is expired
     * @return true if the token is expired or about to expire, false otherwise
     */
    public boolean isTokenExpired() {
        // Consider the token expired if it expires in less than 5 minutes
        return System.currentTimeMillis() > (expiresAt - 300000);
    }

    //retrieve access token
    public String getAccessToken() {
        return accessToken;
    }

    //retrieve refresh token
    public String getRefreshToken() {
        return refreshToken;
    }

    //set refresh token (needed for cases when loading from storage)
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}