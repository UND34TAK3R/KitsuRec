package org.example.kitsurecs.services;

import jakarta.inject.Inject;
import org.example.kitsurecs.config.MalApiConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.kitsurecs.model.Anime;
import org.example.kitsurecs.util.JsonParser;
import org.example.kitsurecs.auth.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@ApplicationScoped
public class MalService {
    private final TokenManager tokenManager;

    @Inject
    public MalService(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public List<Anime> searchAnime(String query) throws IOException {
        // Get the token from the token manager
        String accessToken = tokenManager.getAccessToken();

        // Check if we have a valid access token
        if (accessToken == null) {
            throw new IOException("No access token available. User must authenticate first.");
        }

        //create and send query with every anime fields
        URL url = new URL(MalApiConfig.API_BASE_URL + "/anime?q=" + query + "&limit=5&fields=" + MalApiConfig.ANIME_FIELDS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        //Return the response and Parse it with JSONParser
        String jsonResponse = readResponse(connection);
        return JsonParser.parseAnimeList(jsonResponse);
    }

    public Anime getAnimeDetails(String animeId) throws IOException {
        // Get the token from the token manager
        String accessToken = tokenManager.getAccessToken();

        // Check if we have a valid access token
        if (accessToken == null) {
            throw new IOException("No access token available. User must authenticate first.");
        }

        //create and send query with every anime fields
        URL url = new URL(MalApiConfig.API_BASE_URL + "/anime/" + animeId + "?fields=" + MalApiConfig.ANIME_FIELDS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        //Return the response and Parse it with JSONParser
        String jsonResponse = readResponse(connection);
        return JsonParser.parseAnimeDetails(jsonResponse);
    }

    public List<Anime> browseAnime(int offset, int limit) throws IOException {
        // Get the token from the token manager
        String accessToken = tokenManager.getAccessToken();

        // Check if we have a valid access token
        if (accessToken == null) {
            throw new IOException("No access token available. User must authenticate first.");
        }

        String fields = MalApiConfig.ANIME_FIELDS;
        URL url = new URL(MalApiConfig.API_BASE_URL + "/anime/ranking?ranking_type=all&limit=" + limit +
                "&offset=" + offset + "&fields=" + fields);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        String jsonResponse = readResponse(connection);
        return JsonParser.parseAnimeList(jsonResponse);
    }

    //method to read the responses from the queries
    private String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}