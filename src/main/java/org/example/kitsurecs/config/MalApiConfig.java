//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Entered MAL config for project
// Derrick Mangari      2025/04/21      Entered necessary fields for querying anime
package org.example.kitsurecs.config;

public class MalApiConfig {
    public static final String CLIENT_ID = "68ee568ec35ab7729cb0fe18fc076e98";
    public static final String CLIENT_SECRET = "ebbb47a9fa9b24efbf1a22cd7e9600c03558604908ba215b2a6d7b7d271f403b";
    public static final String TOKEN_URL = "https://myanimelist.net/v1/oauth2/token";
    public static final String API_BASE_URL = "https://api.myanimelist.net/v2";
    public static final String REDIRECT_URI = "http://localhost:8080/KitsuRec/callback";
    public static final String ANIME_FIELDS = "id,title,main_picture,synopsis,mean,genres,media_type,start_date,end_date,num_episodes,average_episode_duration,";

}
