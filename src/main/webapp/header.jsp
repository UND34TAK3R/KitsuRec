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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= title %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #0d6efd;
            --secondary-color: #ff5588;
        }

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

        /* Cards Styles */
        .card {
            transition: background 0.5s, color 0.5s, box-shadow 0.3s;
            overflow: hidden;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        body.light-mode .card {
            background-color: #ffffff;
            color: #212529;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        body.dark-mode .card {
            background-color: #333333;
            color: #f8f9fa;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.4);
        }

        /* Hero Section */
        .hero {
            background-size: cover;
            background-position: center;
            border-radius: 15px;
            position: relative;
            overflow: hidden;
            margin-bottom: 40px;
        }

        body.light-mode .hero {
            background: linear-gradient(rgba(248, 249, 250, 0.7), rgba(248, 249, 250, 0.9)),
            url('/api/placeholder/1920x500') center/cover no-repeat;
        }

        body.dark-mode .hero {
            background: linear-gradient(rgba(31, 31, 31, 0.7), rgba(44, 44, 44, 0.9)),
            url('/api/placeholder/1920x500') center/cover no-repeat;
        }

        .hero-content {
            padding: 40px;
        }

        /* Anime Card */
        .anime-card {
            position: relative;
            height: 100%;
        }

        .anime-image {
            height: 250px;
            position: relative;
        }

        .anime-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }

        .anime-badge {
            position: absolute;
            top: 10px;
            right: 10px;
            padding: 3px 8px;
            border-radius: 4px;
            font-weight: 600;
            font-size: 0.9rem;
        }

        .anime-type {
            position: absolute;
            top: 10px;
            left: 10px;
            padding: 3px 8px;
            border-radius: 4px;
            font-size: 0.8rem;
            font-weight: 600;
            background-color: var(--primary-color);
            color: white;
        }

        .anime-info {
            padding: 15px;
        }

        .continue-watching .card {
            margin-bottom: 15px;
        }

        .continue-image {
            width: 120px;
            min-width: 120px;
            height: 100%;
        }

        .continue-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-top-left-radius: 10px;
            border-bottom-left-radius: 10px;
        }

        .progress {
            height: 5px;
            margin: 10px 0;
        }

        .progress-bar {
            background-color: var(--secondary-color);
        }

        /* Categories */
        .category-card {
            text-align: center;
            transition: all 0.3s;
            cursor: pointer;
            height: 100%;
            padding: 20px 10px;
        }

        .category-card:hover {
            transform: translateY(-5px);
        }

        body.light-mode .category-card:hover {
            background-color: #e9ecef;
        }

        body.dark-mode .category-card:hover {
            background-color: #444444;
        }

        .category-icon {
            font-size: 2rem;
            margin-bottom: 10px;
            color: var(--primary-color);
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .continue-card {
                flex-direction: column;
            }

            .continue-image {
                width: 100%;
                height: 150px;
            }

            .continue-image img {
                border-radius: 10px 10px 0 0;
            }
        }
    </style>
</head>
<body class="light-mode">

<!-- Header Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center" href="index.jsp">
            <img src="${pageContext.request.contextPath}/images/kitsurec-logo.png" alt="KitsuRec logo" style="height: 60px;">
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
