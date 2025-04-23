//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/22      Created Token Manager
// Derrick Mangari      2025/04/23      Added comments

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
    private String codeVerifier;


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
                // if successfully fetched token return true
                this.accessToken = JsonParser.extractToken(response.toString());
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

    //retrieve access token
    public String getAccessToken() {
        return accessToken;
    }
}