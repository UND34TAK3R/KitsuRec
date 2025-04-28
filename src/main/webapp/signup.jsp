<%@include file="header.jsp"%>
<div class="container">
    <h1>Sign Up</h1>

    <%
        String error = request.getParameter("error");
        if (error != null) {
            if (error.equals("invalid-username")) {
                %><div class="error-message">Username must be between 4-15 characters</div><%
            } else if (error.equals("invalid-email")) {
                %><div class="error-message">Please enter a valid email address</div><%
            } else if (error.equals("invalid-password")) {
                %><div class="error-message">Password must be 8-15 characters long and contain at least one number</div><%
            } else if (error.equals("password-not-match")) {
                %><div class="error-message">Passwords do not match</div><%
            } else if (error.equals("internal-error")) {
                %><div class="error-message">An error occurred. Please try again later</div><%
        }
    }
%>

    <form action="signup" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <small>Must be between 4-15 characters</small>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <small>Must be 8-15 characters with at least one number</small>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>
        </div>

        <button type="submit" class="btn-primary">Sign Up</button>
    </form>

    <div class="login-link">
        Already have an account? <a href="login.jsp">Log in</a>
    </div>
</div>
<%@include file="footer.jsp"%>