<%--
  Created by IntelliJ IDEA.
  User: jurattes
  Date: 2025-04-28
  Time: 12:04 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="5;url=login.jsp">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container" style="text-align: center; padding: 50px 0;">
    <div class="checkmark-circle">
        <div class="background"></div>
        <div class="checkmark"></div>
    </div>

    <h1>Signup Successful!</h1>
    <p>Your account has been created successfully.</p>

    <a href="login.jsp" class="btn-primary" style="margin-top: 20px;">Go to Login</a>
</div>

<%@ include file="footer.jsp" %>

<style>
    .checkmark-circle {
        width: 150px;
        height: 150px;
        position: relative;
        display: inline-block;
        vertical-align: top;
    }

    .background {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        background: #4CAF50;
        position: absolute;
    }

    .checkmark {
        position: absolute;
        left: 40px;
        top: 70px;
        width: 60px;
        height: 20px;
        border-left: 5px solid white;
        border-bottom: 5px solid white;
        transform: rotate(-45deg);
        transform-origin: left top;
        animation: draw 0.5s ease-out forwards;
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
</body>
</html>
