//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Created Session Manager to get Users, to check if user is logged in, get username, get profile picture
// Derrick Mangari      2025/04/18      Refactored the structure

package org.example.kitsurecs.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionManager {
    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("userId") != null;
    }

    public static String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("userId");
        }
        return null;
    }

    public static String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("username");
        }
        return null;
    }

    public static String getProfilePicture(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("profilePicture");
        }
        return null;
    }
}
