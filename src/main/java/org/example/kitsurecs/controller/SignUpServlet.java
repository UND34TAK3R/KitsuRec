//History Revision
//      NAME                DATE                    COMMENTS
//  Derrick Mangari       2025/04/24            Created Signup Servlet (need to implement DAOs)

package org.example.kitsurecs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.kitsurecs.model.Role;
import org.example.kitsurecs.model.User;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d).{8,15}$");
    private static final int USERNAME_MAX_LENGTH = 15;
    private static final int USERNAME_MIN_LENGTH = 4;
    private final UserDAO userDAO = new UserDAO(); // Make sure this DAO exists and is implemented

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String profilePicture = "";
        Role role = Role.user;

        try {
            if (username == null || username.length() > USERNAME_MAX_LENGTH || username.length() < USERNAME_MIN_LENGTH) {
                response.sendRedirect("signup.jsp?error=invalid-username");
                return;
            }

            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
                response.sendRedirect("signup.jsp?error=invalid-email");
                return;
            }

            if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
                response.sendRedirect("signup.jsp?error=invalid-password");
                return;
            }

            if (!password.equals(confirmPassword)) {
                response.sendRedirect("signup.jsp?error=password-not-match");
                return;
            }

            User user = new User("", username, email, password, profilePicture, role);
            userDAO.CreateUser(user);

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=internal-error");
        }
    }
}
