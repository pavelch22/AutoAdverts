<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div id="container">
    <div id="header">
        <h1><a href="show_adverts">Auto Adverts</a></h1>
    </div>
    <div id="content">
        <h3>Log In</h3>
        <form action="login" method="post" id="auth-form">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required></div>
            <div>
                <label for="pass">Password:</label>
                <input type="password" id="pass" name="password" required></div>
            <div>
                <input type="submit" class="button" value="Log In">
                <p class="auth-link"><a href="registration">Register</a></p>
            </div>
        </form>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
</body>
</html>
