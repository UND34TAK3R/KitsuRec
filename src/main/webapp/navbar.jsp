<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg">
    <div class="container d-flex justify-content-center">
        <div class="d-flex align-items-center gap-3">
            <!-- Nav buttons -->
            <form action="browse" method="get" class="d-inline">
                <button type="submit" class="btn btn-outline-primary btn-sm d-flex align-items-center">
                    Browse
                </button>
            </form>

            <form action="watchlist.jsp" method="get" class="d-inline">
                <button type="submit" class="btn btn-outline-primary btn-sm d-flex align-items-center">
                    Watchlist
                </button>
            </form>

            <form action="profile.jsp" method="get" class="d-inline">
                <button type="submit" class="btn btn-outline-primary btn-sm d-flex align-items-center">
                    Profile
                </button>
            </form>

            <!-- Conditionally show Login or Logout (WILL BE FIXED LATER) -->
                    <!-- User is logged in, show Logout -->
                    <form action="logout" method="post" class="d-inline">
                        <button type="submit" class="btn btn-outline-danger btn-sm d-flex align-items-center">
                            <i class="bi bi-box-arrow-right me-2"></i> Logout
                        </button>
                    </form>
                    <!-- User is not logged in, show Login -->
                    <form action="login.jsp" method="get" class="d-inline">
                        <button type="submit" class="btn btn-outline-success btn-sm d-flex align-items-center">
                            <i class="bi bi-box-arrow-in-right me-2"></i> Login
                        </button>
                    </form>
        </div>
    </div>
</nav>
