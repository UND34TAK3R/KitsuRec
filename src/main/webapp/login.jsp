<%@ include file="header.jsp" %>

<div class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="card shadow-lg p-4 theme-card" style="max-width: 400px; width: 100%;">
        <h1 class="text-center mb-4 theme-text">Log In</h1>

        <!-- Display error messages if any -->
        <c:if test="${param.error == 'invalid'}">
            <div class="alert alert-danger text-center">
                Invalid email or password. Please try again.
            </div>
        </c:if>
        <c:if test="${param.error == 'server'}">
            <div class="alert alert-danger text-center">
                Server error. Please try again later.
            </div>
        </c:if>

        <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="email" class="form-label theme-text">Email</label>
                <input type="email" id="email" name="email" class="form-control theme-input" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label theme-text">Password</label>
                <input type="password" id="password" name="password" class="form-control theme-input" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Log In</button>
        </form>

        <div class="text-center mt-3">
            <small class="theme-text">
                Don't have an account?
                <a href="signup.jsp" class="text-decoration-none theme-link">Sign up</a>
            </small>
        </div>
    </div>
</div>

<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent normal form submit

        const form = event.target;
        const formData = new URLSearchParams(new FormData(form));

        fetch(form.action, {
            method: 'POST',
            body: formData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(response => {
                if (response.redirected) {
                    window.location.href = '${pageContext.request.contextPath}/mal-login';
                } else {
                    window.location.reload();
                }
            })
            .catch(error => {
                console.error('Login error:', error);
                window.location.reload();
            });
    });
</script>

<%@ include file="footer.jsp" %>
