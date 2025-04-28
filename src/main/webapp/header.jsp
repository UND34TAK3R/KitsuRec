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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title><%= title %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<c:if test="${pageType != 'error' && pageType != 'login' && pageType != 'signup' && pageType != 'admin'}">
    <%@ include file="navbar.jsp" %>
</c:if>