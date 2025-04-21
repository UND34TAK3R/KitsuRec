//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Started it but need to revise structure
// Derrick Mangari      2025/04/21      Finished creating JsonParser Class(needs to be tested)

package org.example.kitsurecs.util;

import org.example.kitsurecs.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static String extractToken(String json) {
        JSONObject obj = new JSONObject(json);
        return obj.getString("access_token");
    }

    public static List<Anime> parseAnimeList(String json) {
        List<Anime> animeList = new ArrayList<>();
        JSONObject obj = new JSONObject(json);
        JSONArray data = obj.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {
            JSONObject node = data.getJSONObject(i).getJSONObject("node");

            int id = node.getInt("id");
            String title = node.getString("title");
            String synopsis = node.getString("synopsis");
            Main_Picture main_picture = null;
            Double mean = node.getDouble("mean");
            List<Genre> genres = new ArrayList<>();
            String media_type = node.has("media_type") ? node.getString("media_type") : "unknown";

            if(node.has("main_picture")) {
                JSONObject picObj = node.getJSONObject("main_picture");
                main_picture = new Main_Picture(
                        picObj.optString("medium", ""),
                        picObj.optString("large", "")
                );
            }
            if (node.has("genres")) {
                JSONArray genreArray = obj.getJSONArray("genres");
                for (int j = 0; j < genreArray.length(); j++) {
                    JSONObject genre = genreArray.getJSONObject(j);
                    genres.add(new Genre(genre.getInt("id"), genre.getString("name")));
                }
            }
            switch (media_type) {
                case "tv":
                    String start_date = node.getString("start_date");
                    String end_date = node.getString("end_date");
                    int num_episode = node.getInt("num_episode");
                    int average_episode_duration = node.getInt("average_episode_duration");
                    animeList.add(new Show(id, title, main_picture, synopsis, mean, genres, media_type, start_date, end_date, num_episode, average_episode_duration));
                case "movie":
                    int duration = node.getInt("average_episode_duration");
                    String movie_release_date = node.getString("start_date");
                    animeList.add(new Movie(id, title, main_picture, synopsis, mean, genres, media_type, duration, movie_release_date));
                default:
                    throw new IllegalArgumentException("Unsupported anime type: " + media_type);
            }
        }
        return animeList;
    }

    public static Anime parseAnimeDetails(String json) {
        JSONObject obj = new JSONObject(json);

        int id = obj.getInt("id");
        String title = obj.getString("title");
        String synopsis = obj.getString("synopsis");
        Main_Picture main_picture = null;
        Double mean = obj.getDouble("mean");
        List<Genre> genres = new ArrayList<>();
        String media_type = obj.has("media_type") ? obj.getString("media_type") : "unknown";

        if(obj.has("main_picture")) {
            JSONObject picObj = obj.getJSONObject("main_picture");
            main_picture = new Main_Picture(
                    picObj.optString("medium", ""),
                    picObj.optString("large", "")
            );
        }
        if (obj.has("genres")) {
            JSONArray genreArray = obj.getJSONArray("genres");
            for (int j = 0; j < genreArray.length(); j++) {
                JSONObject genre = genreArray.getJSONObject(j);
                genres.add(new Genre(genre.getInt("id"), genre.getString("name")));
            }
        }
        switch (media_type) {
            case "tv":
                String start_date = obj.getString("start_date");
                String end_date = obj.getString("end_date");
                int num_episode = obj.getInt("num_episode");
                int average_episode_duration = obj.getInt("average_episode_duration");
                 return new Show(id, title, main_picture, synopsis, mean, genres, media_type, start_date, end_date, num_episode, average_episode_duration);
            case "movie":
                int duration = obj.getInt("average_episode_duration");
                String movie_release_date = obj.getString("release_date");
                return new Movie(id, title, main_picture, synopsis, mean, genres, media_type, duration, movie_release_date);
            default:
                throw new IllegalArgumentException("Unsupported anime type: " + media_type);
        }
    }
}
