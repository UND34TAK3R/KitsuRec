//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/21      Created Class(need to test)
// Derrick Mangari      2025/04/22      Tested
// Derrick Mangari      2025/04/22      Added Comments
package org.example.kitsurecs.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PCKEUtil {

    //method to generate a code verifier needed to access MAL API
    public static String generateCodeVerifier() {
        byte[] code = new byte[64];
        new SecureRandom().nextBytes(code);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    //method to generate a code challenge need to acces MAL API
    public static String generateCodeChallenge(String codeVerifier) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }
}
