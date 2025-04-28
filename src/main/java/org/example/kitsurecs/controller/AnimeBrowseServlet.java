package org.example.kitsurecs.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.kitsurecs.auth.TokenManager;
import org.example.kitsurecs.model.Anime;
import org.example.kitsurecs.services.MalService;

import java.io.IOException;
import java.util.List;

@WebServlet("/browse")
public class AnimeBrowseServlet extends HttpServlet {
    @Inject
    private MalService malService;

    @Override
    public void init() throws ServletException {
        super.init();
        // We'll initialize in doGet if needed
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get TokenManager from session
        HttpSession session = request.getSession();
        TokenManager tokenManager = (TokenManager) session.getAttribute("tokenManager");

        // Check if user is authenticated
        if (tokenManager == null || tokenManager.getAccessToken() == null) {
            // Redirect to login if not authenticated
            response.sendRedirect(request.getContextPath() + "/mal-login");
            return;
        }

        // Use the session's TokenManager for the service if CDI isn't working
        if (malService == null) {
            malService = new MalService(tokenManager);
        }

        int page = 1;
        int limit = 10;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) {
                    page = 1;
                }
            } catch (NumberFormatException e) {
                // Invalid page parameter, stick with default page 1
            }
        }
        int offset = (page - 1) * limit;

        try {
            List<Anime> animeList = malService.browseAnime(offset, limit);
            System.out.println("Anime list size: " + animeList.size()); // Debug log
            request.setAttribute("animeList", animeList);
            request.setAttribute("currentPage", page);

            request.getRequestDispatcher("/browse.jsp").forward(request, response);
        } catch (IOException e) {
            System.err.println("Error in browseAnime: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
            request.setAttribute("errorMsg", "Failed to load anime list: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}