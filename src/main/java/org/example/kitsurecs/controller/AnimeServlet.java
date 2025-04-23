//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Implemented a search by title and get Anime details by its id

package org.example.kitsurecs.controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.example.kitsurecs.model.Anime;
import org.example.kitsurecs.services.MalService;

import java.io.IOException;
import java.util.List;

@Path("/anime")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimeServlet {
    @Inject
    private MalService malService;

    @GET
    @Path("/search")
    public Response searchAnime(@QueryParam("title") String title){
        try{
            List<Anime> result = malService.searchAnime(title);
            return Response.ok().build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching anime" + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getAnimeDetails(@PathParam("id") String id){
        try {
            Anime anime = malService.getAnimeDetails(id);
            return Response.ok().build();
        }catch (IOException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error fetching anime details: " + e.getMessage())
                    .build();
        }
    }
}
