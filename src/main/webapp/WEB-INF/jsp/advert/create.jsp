<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Advert</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>

<div id="container">
    <div id="header">
        <h1><a href="show_adverts">Auto Adverts</a></h1>
        <c:if test="${not empty user}">
            <p>Hello, <strong><em><a href="profile">${user.email}</a></em></strong> <strong><a href="logout">Log Out</a></strong>
            </p>
        </c:if>
    </div>
    <div id="content">
        <h3>New Advert</h3>
        <form action="insert_advert" method="post" enctype="multipart/form-data" id="auth-form">
            <div>
                <label for="model">Model:</label>
                <select name="model" id="model" required>
                    <c:forEach var="model" items="${models}">
                        <option value="${model.id}">${model.brand.name} ${model.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <c:set var="now" value="<%= new Date()%>"/>
                <fmt:formatDate value="${now}" pattern="yyyy" var="y"/>
                <label for="year">Year:</label>
                <input type="number" min="1970" max="${y + 1}" value="${y - 5}" name="year" id="year" required>
            </div>
            <div>
                <label for="engine-type">Engine Type:</label>
                <select name="engineType" id="engine-type" required>
                    <c:forEach var="type" items="${engineTypes}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="engine-volume">Engine Volume:</label>
                <input type="text" name="volume" id="engine-volume" required>
            </div>
            <div>
                <label for="mileage">Mileage:</label>
                <input type="text" name="mileage" id="mileage" required>
            </div>
            <div>
                <label for="color">Color:</label>
                <input type="color" name="color" id="color" required>
            </div>
            <div>
                <label for="price">Price:</label>
                <input type="text" name="price" id="price" required>
            </div>
            <div>
                <label for="photos">Photos:</label>
                <input type="file" name="photo" id="photos" multiple>
            </div>
            <div>
                <label for="comment">Comment:</label>
                <textarea name="comment" id="comment"></textarea>
            </div>
            <div>
                <input type="submit" class="button" value="Create">
            </div>
        </form>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/create.js"></script>
</body>
</html>
