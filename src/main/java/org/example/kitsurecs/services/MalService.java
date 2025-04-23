//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Created this service to get Json Anime Info
//                                      Need to update corresponding to Anime Object
// Derrick Mangari      2025/04/21      Finished creating MalService Class(needs to be tested)
// Derrick Mangari      2025/04/21      Added Comments

package org.example.kitsurecs.services;


import jakarta.inject.Inject;
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
    private final String accessToken;

    //constructors
    @Inject
    public MalService(String accessToken) {
        this.accessToken = accessToken;
    }

    public MalService(){
        this.accessToken = null;
    }

    public List<Anime> searchAnime(String query) throws IOException {
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
        //create and send query with every anime fields
        URL url = new URL(MalApiConfig.API_BASE_URL + "/anime/" + animeId + "?fields=" + MalApiConfig.ANIME_FIELDS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        //Return the response and Parse it with JSONParser
        String jsonResponse = readResponse(connection);
        return JsonParser.parseAnimeDetails(jsonResponse);
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

