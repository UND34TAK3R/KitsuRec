package org.example.kitsurecs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.kitsurecs.config.MalApiConfig;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.example.kitsurecs.util.PCKEUtil.generateCodeChallenge;
import static org.example.kitsurecs.util.PCKEUtil.generateCodeVerifier;

@WebServlet("/mal-login")
public class MalLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // Generate code verifier
        String codeVerifier = generateCodeVerifier();

        // Store in session
        HttpSession session = request.getSession();
        session.setAttribute("code_verifier", codeVerifier);
        System.out.println(codeVerifier);

        // Generate auth URL using this code verifier
        String codeChallenge = codeVerifier; // For method="plain"
        String authUrl = "https://myanimelist.net/v1/oauth2/authorize" +
                "?response_type=code" +
                "&client_id=" + MalApiConfig.CLIENT_ID +
                "&redirect_uri=" + URLEncoder.encode(MalApiConfig.REDIRECT_URI, StandardCharsets.UTF_8) +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=plain";

        // Redirect to auth URL
        response.sendRedirect(authUrl);
    }
}