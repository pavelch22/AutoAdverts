<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div id="container">
    <div id="header">
        <h1><a href="show_adverts">Auto Adverts</a></h1>
    </div>
    <div id="content">
        <h3>Registration</h3>
        <form action="register" method="post" id="auth-form">
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div>
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" required>
            </div>
            <div>
                <label for="city">City</label>
                <input type="text" id="city" name="city" required>
            </div>
            <div>
                <label for="pass">Password:</label>
                <input type="password" id="pass" name="password" required>
            </div>
            <div>
                <label for="spass">Re-enter password:</label>
                <input type="password" id="spass" required>
            </div>
            <div>
                <input type="reset" class="button" value="Clear">
                <input type="submit" class="button" value="Register">
                <p class="auth-link"><a href="authentication">Log In</a></p>
            </div>
        </form>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
</body>
</html>