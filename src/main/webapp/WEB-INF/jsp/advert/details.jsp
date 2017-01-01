<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advert Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/fotorama-4.6.2/fotorama.css">
    <script src="${pageContext.request.contextPath}/lib/fotorama-4.6.2/fotorama.js"></script>
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
        <table align="center">
            <tr>
                <td>Advert Id</td>
                <td>${advert.id}</td>
            </tr>
            <tr>
                <td>User Id</td>
                <td>${advert.user.id}</td>
            </tr>
            <tr>
                <td>User Email</td>
                <td>${advert.user.email}</td>
            </tr>
            <tr>
                <td>User Phone</td>
                <td>${advert.user.phone}</td>
            </tr>
            <tr>
                <td>User City</td>
                <td>${advert.user.city}</td>
            </tr>
            <tr>
                <td>Model</td>
                <td>${advert.model.brand.name} ${advert.model.name}</td>
            </tr>
            <tr>
                <td>Year</td>
                <td>${advert.year}</td>
            </tr>
            <tr>
                <td>Engine Type</td>
                <td>${advert.engineType.name}</td>
            </tr>
            <tr>
                <td>Engine Volume</td>
                <td>${advert.engineVolume}</td>
            </tr>
            <tr>
                <td>Mileage</td>
                <td>${advert.mileage}</td>
            </tr>
            <tr>
                <td>Color</td>
                <td>${advert.color}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${advert.price}</td>
            </tr>
            <tr>
                <td>Comment</td>
                <td>${advert.comment}</td>
            </tr>
        </table>
        <div class="fotorama"
             data-width="960"
             data-height="540"
             data-fit="contain"
             data-navposition="bottom"
             data-nav="thumbs"
             data-thumbwidth="128"
             data-thumbheight="96"
             data-allowfullscreen="true">
            <c:forEach var="photo" items="${advert.photos}">
                <img src="print_image?id=${photo.id}">
            </c:forEach>
        </div>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
</body>
</html>
