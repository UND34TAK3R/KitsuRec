//Revision History:
//      NAME              DATE                        COMMENTS
// Derrick Mangari      2025/04/15      Added Login Servlet, creates a session token and adds attributes userId, username and profile picture in session token
//                                      Need to add DAO to finish
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
import org.example.kitsurecs.model.User;
import org.example.kitsurecs.util.CookieManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    //allows user to login
    //need to implement userDAO field
    //private final UserDAO userDAO = new UserDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get the email and password from user
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //find the users account in db with email
        User user = userDAO.findByEmail(email);

        try{
            //if user is not null and password matches get userID, username and profile_picture
            if (user != null && user.checkPassword(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userID", user.getUser_id());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("profile_picture", user.getProfile_picture());

                //return true or false if request is secure
                boolean isSecure = request.isSecure();

                //creates cookie with user info
                CookieManager.createCookie(response, "JSESSIONID", session.getId(), -1, isSecure);

                //redirect the user to the homepage
                response.sendRedirect("index.jsp");
            }
            else{
                //stays in login page
                response.sendRedirect("login.jsp?error=invalid");
            }
        } catch (Exception e) {
            //log error
            System.out.println("User could not login : " + e.getMessage());
            response.sendRedirect("login.jsp?error=server");
        }
    }
}
