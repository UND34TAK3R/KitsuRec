package org.example.kitsurecs.test;

import org.example.kitsurecs.config.MalApiConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.example.kitsurecs.util.PCKEUtil.generateCodeVerifier;

public class GetMalAuthUrl {
    public static void main(String[] args) throws Exception {
        // Generate a code verifier (random string between 43-128 chars)
        String codeVerifier = generateCodeVerifier();
        System.out.println("Code verifier: " + codeVerifier);

        // For basic PKCE, code_challenge is the same as code_verifier when using plain method
        String codeChallenge = codeVerifier;

        String authUrl = "https://myanimelist.net/v1/oauth2/authorize" +
                "?response_type=code" +
                "&client_id=" + MalApiConfig.CLIENT_ID +
                "&redirect_uri=" + URLEncoder.encode(MalApiConfig.REDIRECT_URI, StandardCharsets.UTF_8) +
                "&code_challenge=" + codeChallenge +
                "&code_challenge_method=plain";

        System.out.println("Open this URL in your browser:");
        System.out.println(authUrl);
    }


}