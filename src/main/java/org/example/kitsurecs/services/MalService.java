//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Created this service to get Json Anime Info
//                                      Need to update corresponding to Anime Object
// Derrick Mangari      2025/04/21      Finished creating MalService Class(needs to be tested)

package org.example.kitsurecs.services;


import org.example.kitsurecs.config.MalApiConfig;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.kitsurecs.model.Anime;
import org.example.kitsurecs.util.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@ApplicationScoped
public class MalService {
    private String accessToken;

    private void fetchAccessToken() throws IOException {
        URL url = new URL(MalApiConfig.TOKEN_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String params = "grant_type=client_credentials"
                + "&client_id=" + MalApiConfig.CLIENT_ID
                + "&client_secret=" + MalApiConfig.CLIENT_SECRET;

        try(OutputStream os = connection.getOutputStream()) {
            os.write(params.getBytes());
        }
        String response = readResponse(connection);
        this.accessToken = JsonParser.extractToken(response);
    }

    public List<Anime> searchAnime(String query) throws IOException {
        if (accessToken == null) {
            fetchAccessToken();
        }
        URL url = new URL(MalApiConfig.API_BASE_URL + "/anime?q=" + query + "&limit=5&fields=" + MalApiConfig.ANIME_FIELDS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        String jsonResponse = readResponse(connection);
        return JsonParser.parseAnimeList(jsonResponse);
    }

    public Anime getAnimeDetails(String animeId) throws IOException {
        if (accessToken == null) {
            fetchAccessToken();
        }

        URL url = new URL(MalApiConfig.API_BASE_URL + "/anime/" + animeId + "?fields="+ MalApiConfig.ANIME_FIELDS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        String jsonResponse = readResponse(connection);
        return JsonParser.parseAnimeDetails(jsonResponse);
    }

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
