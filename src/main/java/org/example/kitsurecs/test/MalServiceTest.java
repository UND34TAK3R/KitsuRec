package org.example.kitsurecs.test;

import org.example.kitsurecs.auth.TokenManager;
import org.example.kitsurecs.model.Anime;
import org.example.kitsurecs.services.MalService;

import java.io.IOException;
import java.util.List;

public class MalServiceTest {

    public static void main(String[] args) {
        // Initialize the TokenManager and MalService
        TokenManager tokenManager = new TokenManager();

        // Replace this with the actual authorization code you get from the OAuth2 flow
        String authorizationCode = "def5020079ce5558d5f93b804316b39bec9a84398cbc30ef14c9b9c45e08db6ee1c34fb03cad452d40a652c6a1db2d3b570d956dd9d337743f7beb7d8f6db55173c9b979b0e83896e29cc341fbb3043e2f64857512fe94ea0e2c8a6ca638508306e4640457be052fbc72e6291870763a1613a02ca13f6be732cd7639e35d0be6d1329fc37ab8bffd766e3d4bdb7e48dd8cc44d20f2d16fb9198fe66442f9e59aca777e12f0b0cb6bd52bcf115bd5d2c6b6edc9bf96fb86cdd44f0aa4a27770b9b3163974c2aba63d2f9464bad77a8244b2bbde0e032acffa8a47177798c0623a63494ee587011fa0fd9ff640cf2e924f8d95fb0f85664cc447b17c988db0828ddaf63231c1c3a56447af991bd985e59f3bbe26aa654d64025caca256c5db032f9c82dad0c1a9c6ee92e53c93d8e7c8b62317b771f711fe83886c8163ce8795887a10744762a32e55fdbc49b61504fdb629578281eb735b48f24b3c043405d4986694f1c50a3bd8c2f2ed5025756b3652ab6b28224c4933fef3d64c0e5da11914d184d5e50165c9d2cea72fcd5b50fa6ea13187d2581e952958dcb7f49ad10094abd3d048c1f1affa45e35b450411ec9b595d128baa5da84051d4d6d88c57f5c7ef98ec8dc07923b28cb30c60d952faf7a8815c0b1338ee8ec8f2a1e54eaf80e06a5346c072e8152439";
        String codeVerifier = "gwKYEsoLehJNkb-_wy4thcYdp9sHdAB3P9J9AF9qjOuZ0q4BcdSR3cPl6mSAtR5Mji_KNv4rhV5cW5YAv-u6TQ";

        // Fetch the access token
        if (tokenManager.fetchAccessToken(authorizationCode, codeVerifier)) {
            System.out.println("Successfully authenticated!");

            String accessToken = tokenManager.getAccessToken();

            // Create the MalService with the tokenManager after authentication
            MalService malService = new MalService(accessToken);

            try {
                // Test: Search for an anime
                System.out.println("Searching for 'Naruto'...");
                List<Anime> animeList = malService.searchAnime("Naruto");

                if (animeList != null && !animeList.isEmpty()) {
                    System.out.println("✅ Search Success! Found:");
                    for (Anime anime : animeList) {
                        // Using getId() instead of getAnime_id()
                        System.out.println(" - " + anime.getTitle() + " (ID: " + anime.getAnime_id() + ")");
                    }
                } else {
                    System.out.println("❌ No results found.");
                }

                // If search succeeded, fetch details of the first anime
                if (!animeList.isEmpty()) {
                    // Using getId() instead of getAnime_id()
                    String animeId = String.valueOf(animeList.get(0).getAnime_id());
                    System.out.println("\nFetching details for Anime ID: " + animeId);
                    Anime detailedAnime = malService.getAnimeDetails(animeId);

                    if (detailedAnime != null) {
                        System.out.println("✅ Details Loaded:");
                        System.out.println("Title: " + detailedAnime.getTitle());
                        System.out.println("Type: " + detailedAnime.getMediaType());
                        System.out.println("Genres: " + detailedAnime.getGenres());
                    } else {
                        System.out.println("❌ Failed to load anime details.");
                    }
                }

            } catch (IOException e) {
                System.out.println("💥 API Test Failed: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ Authentication failed!");
        }
    }
}