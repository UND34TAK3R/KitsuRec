package org.example.kitsurecs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.kitsurecs.auth.TokenManager;
import org.example.kitsurecs.config.MalApiConfig;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.example.kitsurecs.util.PCKEUtil.generateCodeChallenge;
import static org.example.kitsurecs.util.PCKEUtil.generateCodeVerifier;

@WebServlet("/mal-login")
public class MalLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        TokenManager tokenManager = (TokenManager) session.getAttribute("tokenManager");

        // If we have a token manager with a refresh token, try to refresh
        if (tokenManager != null && tokenManager.getRefreshToken() != null) {
            boolean refreshed = tokenManager.refreshAccessToken(tokenManager.getRefreshToken());
            if (refreshed) {
                // Successfully refreshed, redirect to main app
                response.sendRedirect(request.getContextPath()+"/home");
                return;
            }
        }

        // If we get here, we need to do the full auth flow
        String codeVerifier = generateCodeVerifier();
        session.setAttribute("code_verifier", codeVerifier);

        // If using plain method (the code verifier itself is the challenge)
        String codeChallenge = codeVerifier;
        String codeChallengeMethod = "plain";

// OR if using S256 method (which appears to be what your code is actually doing)
// String codeChallenge = generateCodeChallenge(codeVerifier); // Base64URL(SHA256(codeVerifier))
// String codeChallengeMethod = "S256";

        String authUrl = "https://myanimelist.net/v1/oauth2/authorize" +
                "?response_type=code" +
                "&client_id=" + MalApiConfig.CLIENT_ID +
                "&redirect_uri=" + URLEncoder.encode(MalApiConfig.REDIRECT_URI, StandardCharsets.UTF_8) +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=" + codeChallengeMethod;

        response.sendRedirect(authUrl);
    }
}