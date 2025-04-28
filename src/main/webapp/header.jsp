<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String uri = request.getRequestURI();
    String title = "KitsuRec";
    String pageType = "";

    if (uri.contains("signup")) {
        title += " - Sign Up";
        pageType = "signup";

    } else if (uri.contains("login")) {
        title += " - Login";
        pageType = "login";

    } else if (uri.contains("browse")) {
        title += " - Browse";

    } else if (uri.contains("profile")) {
        title += " - Profile";

    } else if (uri.contains("watchlist")) {
        title += " - Watch List";

    } else if (uri.contains("error")) {
        title += " - Error";
        pageType = "error";

    } else if (uri.contains("admin")) {
        title += " - Admin";
        pageType = "admin";
    } else if (uri.contains("home")) {
        title += " - Welcome";
    }
    request.setAttribute("pageType", pageType);
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%= title %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body.light-mode {
            background: #f8f9fa;
            color: #212529;
            transition: background 0.5s, color 0.5s;
        }
        body.dark-mode {
            background: linear-gradient(135deg, #1f1f1f 0%, #2c2c2c 100%);
            color: #f8f9fa;
            transition: background 0.5s, color 0.5s;
        }
        footer.bg-dark {
            background: linear-gradient(135deg, #1f1f1f 0%, #2c2c2c 100%) !important;
            transition: background 0.5s;
        }
        footer.bg-light {
            background: #f8f9fa !important;
            transition: background 0.5s;
        }
        a {
            transition: color 0.2s ease;
        }
    </style>
</head>
<body class="light-mode">

<!-- Header Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container">
        <a class = "navbar-brand d-flex align-items-center" href = "index.jsp">
            <img src = "${pageContext.request.contextPath}/images/kitsurec-logo.png" alt = "KitsuRec logo" style = "height: 60px;">
        </a>
        <div class="d-flex">
            <button id="themeToggle" class="btn btn-outline-primary ms-2">
                <span id="themeIcon">🌙</span>
            </button>
        </div>
    </div>
</nav>

<!-- Include the Navbar based on pageType -->
<c:if test="${pageType != 'error' && pageType != 'login' && pageType != 'signup' && pageType != 'admin'}">
    <%@ include file="navbar.jsp" %>
</c:if>

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const theme = localStorage.getItem('theme') || 'light';
        setTheme(theme);
    });

    function setTheme(theme) {
        const body = document.body;
        const navbar = document.querySelector('.navbar');
        const footer = document.querySelector('footer');
        const themeIcon = document.getElementById('themeIcon');

        if (theme === 'dark') {
            body.classList.remove('light-mode');
            body.classList.add('dark-mode');
            if (navbar) {
                navbar.classList.remove('navbar-light', 'bg-light');
                navbar.classList.add('navbar-dark', 'bg-dark');
            }
            if (footer) {
                footer.classList.remove('bg-light', 'text-dark');
                footer.classList.add('bg-dark', 'text-light');
            }
            themeIcon.textContent = '☀️'; // Sun for light mode next
        } else {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
            if (navbar) {
                navbar.classList.remove('navbar-dark', 'bg-dark');
                navbar.classList.add('navbar-light', 'bg-light');
            }
            if (footer) {
                footer.classList.remove('bg-dark', 'text-light');
                footer.classList.add('bg-light', 'text-dark');
            }
            themeIcon.textContent = '🌙'; // Moon for dark mode next
        }
    }

    document.getElementById('themeToggle').addEventListener('click', () => {
        const currentTheme = document.body.classList.contains('dark-mode') ? 'dark' : 'light';
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        localStorage.setItem('theme', newTheme);
        setTheme(newTheme);
    });
</script>
</body>
</html>
