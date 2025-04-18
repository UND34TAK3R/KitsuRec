//Revision History:
//      NAME              DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Added Login Servlet, creates a session token and adds attributes userId, username and profile picture in session token
//                                      Need to add DAO to finish
// Derrick Mangari      2025/04/18      Refactored the structure

package org.example.kitsurecs.controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.kitsurecs.model.User;
import org.example.kitsurecs.util.CookieManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    //need to implement userDAO field
    //private final UserDAO userDAO = new UserDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userDAO.findByEmail(email);

        try{
            if (user != null && user.checkPassword(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userID", user.getUser_id());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("profile_picture", user.getProfile_picture());

                boolean isSecure = request.isSecure();
                CookieManager.createCookie(response, "JSESSIONID", session.getId(), -1, isSecure);

                response.sendRedirect("index.jsp");
            }
            else{
                response.sendRedirect("login.jsp?error=invalid");
            }
        } catch (Exception e) {
            System.out.println("User could not login : " + e.getMessage());
            response.sendRedirect("login.jsp?error=server");
        }
    }
}
