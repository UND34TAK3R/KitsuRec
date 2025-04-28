//Revision History:
//      NAME            DATE                        COMMENTS
// Derrick Mangari  2025/04/15      Added logout servlet (deletes user session token)
// Derrick Mangari      2025/04/18      Refactored the structure
// Derrick Mangari      2025/04/22      Added Comments
package org.example.kitsurecs.controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //gets the session
        HttpSession session = request.getSession(false);
        //if session is not null it invalidates the current session
        if (session != null) {
            session.invalidate();
        }
        //send the user directly to the homepage
        response.sendRedirect(request.getContextPath()+"login.jsp");
    }
}
