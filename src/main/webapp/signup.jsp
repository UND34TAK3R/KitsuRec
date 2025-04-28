<%@ include file="header.jsp" %>

<div class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="card shadow-lg p-4 theme-card" style="max-width: 400px; width: 100%;">
        <h1 class="text-center mb-4 theme-text">Sign Up</h1>

        <%
            String error = request.getParameter("error");
            if (error != null) {
                String errorMessage = "";
                if (error.equals("invalid-username")) {
                    errorMessage = "Username must be between 4-15 characters";
                } else if (error.equals("invalid-email")) {
                    errorMessage = "Please enter a valid email address";
                } else if (error.equals("email-already-exists")) {
                    errorMessage = "This email is already registered. Please login or use another email to register";
                } else if (error.equals("invalid-password")) {
                    errorMessage = "Password must be 8-15 characters long and contain at least one number";
                } else if (error.equals("password-not-match")) {
                    errorMessage = "Passwords do not match";
                } else if (error.equals("internal-error")) {
                    errorMessage = "An error occurred. Please try again later";
                }
                if (!errorMessage.isEmpty()) {
        %>
        <div class="alert alert-danger text-center">
            <%= errorMessage %>
        </div>
        <%
                }
            }
        %>

        <form action="signup" method="post">
            <div class="mb-3">
                <label for="username" class="form-label theme-text">Username</label>
                <input type="text" id="username" name="username" class="form-control theme-input" required>
                <div class="form-text theme-text">Must be between 4-15 characters</div>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label theme-text">Email</label>
                <input type="email" id="email" name="email" class="form-control theme-input" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label theme-text">Password</label>
                <input type="password" id="password" name="password" class="form-control theme-input" required>
                <div class="form-text theme-text">Must be 8-15 characters with at least one number</div>
            </div>

            <div class="mb-3">
                <label for="confirmPassword" class="form-label theme-text">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control theme-input" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Sign Up</button>
        </form>

        <div class="text-center mt-3">
            <small class="theme-text">Already have an account? <a href="login.jsp" class="text-decoration-none theme-link">Log in</a></small>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
