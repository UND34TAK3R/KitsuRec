package org.example.kitsurecs.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.kitsurecs.model.Anime;
import org.example.kitsurecs.services.MalService;

import java.io.IOException;
import java.util.List;

@WebServlet("/browse")
public class AnimeBrowseServlet extends HttpServlet {
    @Inject
    private MalService malService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int limit = 10;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try{
                page = Integer.parseInt(pageParam);
                if (page < 1){
                    page = 1;
                }
            }catch (NumberFormatException e){

            }
        }
        int offset = (page - 1) * limit;

        try{
            List<Anime> animeList = malService.browseAnime(offset, limit);
            request.setAttribute("animeList", animeList);
            request.setAttribute("currentPage", page);

            request.getRequestDispatcher("/browse.jsp").forward(request, response);
        }catch (IOException e){
            request.setAttribute("errorMsg", "Failed to load anime list : "+e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
