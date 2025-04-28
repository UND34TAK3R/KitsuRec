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

        String userid = UUID.randomUUID().toString();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String profilePicture = "";
        Role role = Role.user;

        try {
            if (username == null || username.length() > USERNAME_MAX_LENGTH || username.length() < USERNAME_MIN_LENGTH) {
                response.sendRedirect(request.getContextPath()+"signup.jsp?error=invalid-username");
                return;
            }

            if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
                response.sendRedirect(request.getContextPath()+"signup.jsp?error=invalid-email");
                return;
            }

            if (usersDAO.emailExists(email)) {
                response.sendRedirect(request.getContextPath()+"signup.jsp?error=email-already-exists");
            }

            if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
                response.sendRedirect(request.getContextPath()+"signup.jsp?error=invalid-password");
                return;
            }

            if (!password.equals(confirmPassword)) {
                response.sendRedirect(request.getContextPath()+"signup.jsp?error=password-not-match");
                return;
            }

            usersDAO.registerUser(0, username, email, password);

            User temp = new User(0, username, email, password, "", role);
            String hashedPW = temp.hashPassword(password);
            usersDAO.registerUserToDatabase(0, username, email, hashedPW);

            response.sendRedirect(request.getContextPath()+"/login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"signup.jsp?error=internal-error");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the signup page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
}
