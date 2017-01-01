<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div id="container">
    <div id="header">
        <h1><a href="show_adverts">Auto Adverts</a></h1>
        <c:if test="${not empty user}">
            <p>Hello, <strong><em><a href="profile">${user.email}</a></em></strong> <strong><a href="logout">Log Out</a></strong></p>
        </c:if>
        <c:if test="${empty user}">
            <p><strong><a href="authentication">Log In</a></strong> <strong>/</strong> <strong><a href="registration">Register</a></strong></p>
        </c:if>
    </div>
    <div id="content">
        <h3>Error</h3>
        <c:if test="${not empty message}">
            <p class="error-message">${message}</p>
        </c:if>
        <c:if test="${empty message}">
            <p class="error-message">Something went wrong...</p>
        </c:if>
        <p class="go-back"><a href="show_adverts">Go to the main page</a></p>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
</body>
</html>
