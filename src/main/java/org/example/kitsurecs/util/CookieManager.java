//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Added Cookie Manager to create, get and delete a Cookie
// Derrick Mangari      2025/04/18      Refactored the structure

package org.example.kitsurecs.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {
    public static void createCookie(HttpServletResponse response, String name, String value, int maxAge, boolean isSecure){
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setSecure(isSecure);
        response.addCookie(cookie);
    }

    public static Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }

    public static void deleteCookie(HttpServletResponse response, String name){
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
