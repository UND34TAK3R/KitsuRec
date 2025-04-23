//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Started it but need to revise structure
// Derrick Mangari      2025/04/21      Finished creating JsonParser Class(needs to be tested)
// Derrick Mangari      2025/04/22      Tested and made few modifications
// Derrick Mangari      2025/04/23      added comments

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

    //Method to Parse a list of Anime
    public static List<Anime> parseAnimeList(String json) {
        //crate List(will contain a list of Animes)
        List<Anime> animeList = new ArrayList<>();
        //create json object from the json passed(contains the anime list not parsed)
        JSONObject obj = new JSONObject(json);
        //Create JSON Array from the List of Anime(not parsed)
        JSONArray data = obj.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {
            //Within the previous Object create a new json object called node
            JSONObject node = data.getJSONObject(i).getJSONObject("node");

            //Get every fields necessary from one node(one anime)
            int id = node.getInt("id");
            String title = node.getString("title");
            String synopsis = node.getString("synopsis");
            Main_Picture main_picture = null;
            Double mean = node.getDouble("mean");
            List<Genre> genres = new ArrayList<>();
            String media_type = node.has("media_type") ? node.getString("media_type") : "unknown";

            //checks if it has a main picture and retrieves both medium and large image format
            if(node.has("main_picture")) {
                JSONObject picObj = node.getJSONObject("main_picture");
                main_picture = new Main_Picture(
                        picObj.optString("medium", ""),
                        picObj.optString("large", "")
                );
            }

            //if it has genres it retrieves every genre the node has
            if (node.has("genres")) {
                JSONArray genreArray = node.getJSONArray("genres"); // ✅ correct source
                for (int j = 0; j < genreArray.length(); j++) {
                    JSONObject genre = genreArray.getJSONObject(j);
                    genres.add(new Genre(genre.getInt("id"), genre.getString("name")));
                }
            }


            switch (media_type) {
                //if a tv show it creates a Show Object and adds it to the AnimeList
                case "tv":
                    String start_date = node.optString("start_date", "");
                    String end_date = node.optString("end_date", "");
                    int num_episodes = node.optInt("num_episodes", 0);
                    int average_episode_duration = node.optInt("average_episode_duration", 0);
                    animeList.add(new Show(id, title, main_picture, synopsis, mean, genres, media_type, start_date, end_date, num_episodes, average_episode_duration));
                    break;
                //if a movie it creates a Movie object and adds it to the AnimeList
                case "movie":
                    int duration = node.optInt("average_episode_duration", 0);
                    String movie_release_date = node.optString("start_date", "");
                    animeList.add(new Movie(id, title, main_picture, synopsis, mean, genres, media_type, duration, movie_release_date));
                    break;
                //if not a movie or a show we don't handle it
                default:
                    throw new IllegalArgumentException("Unsupported anime type: " + media_type);
            }
        }
        //return the anime list
        return animeList;
    }

    public static Anime parseAnimeDetails(String json) {
        //creates a json object from the anime retrieved
        JSONObject obj = new JSONObject(json);

        //Get every fields necessary from the object
        int id = obj.getInt("id");
        String title = obj.getString("title");
        String synopsis = obj.getString("synopsis");
        Main_Picture main_picture = null;
        Double mean = obj.getDouble("mean");
        List<Genre> genres = new ArrayList<>();
        String media_type = obj.has("media_type") ? obj.getString("media_type") : "unknown";

        //checks if it has a main picture and retrieves both medium and large image format
        if(obj.has("main_picture")) {
            JSONObject picObj = obj.getJSONObject("main_picture");
            main_picture = new Main_Picture(
                    picObj.optString("medium", ""),
                    picObj.optString("large", "")
            );
        }

        //if it has genres it retrieves every genre the object has
        if (obj.has("genres")) {
            JSONArray genreArray = obj.getJSONArray("genres");
            for (int j = 0; j < genreArray.length(); j++) {
                JSONObject genre = genreArray.getJSONObject(j);
                genres.add(new Genre(genre.getInt("id"), genre.getString("name")));
            }
        }


        switch (media_type) {

            //if a tv show it creates a Show Object and return it
            case "tv":
                String start_date = obj.getString("start_date");
                String end_date = obj.getString("end_date");
                int num_episodes = obj.getInt("num_episodes");
                int average_episode_duration = obj.getInt("average_episode_duration");
                 return new Show(id, title, main_picture, synopsis, mean, genres, media_type, start_date, end_date, num_episodes, average_episode_duration);
            //if a movie, it creates a Movie Object and return it
            case "movie":
                int duration = obj.getInt("average_episode_duration");
                String movie_release_date = obj.getString("start_date");
                return new Movie(id, title, main_picture, synopsis, mean, genres, media_type, duration, movie_release_date);
            //Otherwise if not movie or show me don't handle it
            default:
                throw new IllegalArgumentException("Unsupported anime type: " + media_type);
        }
    }
}
