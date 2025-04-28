//History Revision
//      NAME                DATE                    COMMENTS
//  Derrick Mangari       2025/04/24            Created Signup Servlet (need to implement DAOs)j

package org.example.kitsurecs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.kitsurecs.dao.UsersDAO;
import org.example.kitsurecs.dao.impl.UsersDAOImpl;
import org.example.kitsurecs.model.Role;
import org.example.kitsurecs.model.User;
import java.util.UUID;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*\\d).{8,15}$");
    private static final int USERNAME_MAX_LENGTH = 15;
    private static final int USERNAME_MIN_LENGTH = 4;
    UsersDAO usersDAO = new UsersDAOImpl(); // Make sure this DAO exists and is implemented

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // All user fields
        String userid = UUID.randomUUID().toString();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String profilePicture = "";
        Role role = Role.user;

        try {
            // Checks if the username is between 4 and 13 characters
            if (username == null || username.length() > USERNAME_MAX_LENGTH || username.length() < USERNAME_MIN_LENGTH) {
                response.sendRedirect(request.getContextPath()+"/signup.jsp?error=invalid-username");
                return;
            }

            // Checks the regex patterns and if any value of email is inserted
            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
                response.sendRedirect(request.getContextPath()+"/signup.jsp?error=invalid-email");
                return;
            }

            // Checks if email already exists
            if (usersDAO.emailExists(email.toLowerCase())) {
                response.sendRedirect(request.getContextPath()+"/signup.jsp?error=email-already-exists");
                return;
            }

            // Checks if password matches criteria or if it's null
            if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
                response.sendRedirect(request.getContextPath()+"/signup.jsp?error=invalid-password");
                return;
            }

            // Checks if the confirmed password is the same as the first password
            if (!password.equals(confirmPassword)) {
                response.sendRedirect(request.getContextPath()+"/signup.jsp?error=password-not-match");
                return;
            }

            // Create temp user to hash password
            User temp = new User(userid, username, email, password, profilePicture, role);
            String hashedPW = temp.hashPassword(password);

            // Registers user to the database
            usersDAO.registerUser(userid, username, hashedPW, email);

            response.sendRedirect(request.getContextPath()+"/signup-success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/signup.jsp?error=internal-error");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the signup page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
