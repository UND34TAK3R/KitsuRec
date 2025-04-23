package org.example.kitsurecs.test;

import org.example.kitsurecs.auth.TokenManager;
import org.example.kitsurecs.config.MalApiConfig;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTokenQuick {
    public static void main(String[] args) {
        try {
            // Create a TokenManager
            TokenManager tokenManager = new TokenManager();

            // Use client_credentials flow for testing (no authorization code needed)
            URL url = new URL(MalApiConfig.TOKEN_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String params = "grant_type=client_credentials" +
                    "&client_id=" + MalApiConfig.CLIENT_ID +
                    "&client_secret=" + MalApiConfig.CLIENT_SECRET;

            try(OutputStream os = connection.getOutputStream()) {
                os.write(params.getBytes());
            }

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Token response: " + response.toString());

            // Extract token
            JSONObject obj = new JSONObject(response.toString());
            String token = obj.getString("access_token");
            System.out.println("Access token: " + token);

            // Now you can make API calls with this token
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}