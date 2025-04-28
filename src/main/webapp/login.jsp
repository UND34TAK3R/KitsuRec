<%@include file="header.jsp"%>
<header>
    <h1>Welcome to KitsuRecs</h1>
</header>

<main>
    <div class="login-container">
        <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
            <h2>Login</h2>

            <!-- Display error messages if any -->
            <c:if test="${param.error == 'invalid'}">
                <p class="error">Invalid email or password. Please try again.</p>
            </c:if>
            <c:if test="${param.error == 'server'}">
                <p class="error">Server error. Please try again later.</p>
            </c:if>

            <!-- Email field -->
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required />
            </div>

            <!-- Password field -->
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required />
            </div>

            <!-- Login button -->
            <button type="submit">Login</button>
        </form>
    </div>
</main>

<!-- JavaScript to handle login and redirect to MAL login -->
<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Stop normal form submission

        const form = event.target;
        const formData = new URLSearchParams(new FormData(form)); // <-- changed here!

        fetch(form.action, {
            method: 'POST',
            body: formData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' // <-- also set this!
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
<%@include file="footer.jsp"%>