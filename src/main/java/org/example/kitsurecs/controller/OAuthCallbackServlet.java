//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Created this class to get the auth code from MAL

package org.example.kitsurecs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.kitsurecs.auth.TokenManager;

import java.io.IOException;

@WebServlet("/callback")
public class OAuthCallbackServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        // Retrieve the code verifier from session
        HttpSession session = request.getSession();
        String codeVerifier = (String) session.getAttribute("code_verifier");

        // Use code and codeVerifier to get the token
        TokenManager tokenManager = new TokenManager();  // <-- This creates a new TokenManager
        boolean isAuthenticated = tokenManager.fetchAccessToken(code, codeVerifier);

        // Store tokenManager in session if authenticated
        if (isAuthenticated) {
            session.setAttribute("tokenManager", tokenManager);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            response.getWriter().write("Authentication failed.");
        }
    }
}
