<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="refresh" content="5;url=login.jsp">
    <meta charset="UTF-8">
    <title>Signup Successful</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

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
        .checkmark-circle {
            width: 150px;
            height: 150px;
            position: relative;
            display: inline-block;
            margin: 0 auto; /* This centers the checkmark-circle horizontally */
            vertical-align: middle;
        }

        .background {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            background: #4CAF50;
            position: absolute;
            top: 0;
            left: 0;
        }

        .checkmark {
            position: absolute;
            left: 50%;
            top: 50%;
            width: 60px;
            height: 20px;
            border-left: 5px solid white;
            border-bottom: 5px solid white;
            transform: rotate(-45deg);
            transform-origin: left top;
            animation: draw 0.5s ease-out forwards;
            margin-left: -30px; /* Adjusts the checkmark to center it */
            margin-top: -10px; /* Adjusts the checkmark to center it */
        }

        @keyframes draw {
            0% {
                width: 0;
                height: 0;
            }
            50% {
                width: 60px;
                height: 0;
            }
            100% {
                width: 60px;
                height: 20px;
            }
        }
    </style>
</head>

<body class="light-mode">
<div class="container d-flex flex-column align-items-center justify-content-center min-vh-100 text-center">
    <div class="checkmark-circle mb-4">
        <div class="background"></div>
        <div class="checkmark"></div>
    </div>

    <h1>Signup Successful!</h1>
    <p>Your account has been created successfully.</p>

    <a href="login.jsp" class="btn btn-primary mt-3">Go to Login</a>
</div>

<!-- Dark Mode Script -->
<script>
    document.addEventListener('DOMContentLoaded', () => {
        const theme = localStorage.getItem('theme') || 'light';
        setTheme(theme);
    });

    function setTheme(theme) {
        const body = document.body;

        if (theme === 'dark') {
            body.classList.remove('light-mode');
            body.classList.add('dark-mode');
        } else {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
        }
    }
</script>

</body>
</html>
