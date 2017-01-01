<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adverts</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/DataTables/datatables.min.css">
    <script src="${pageContext.request.contextPath}/lib/DataTables/datatables.min.js"></script>
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
        <h3>Adverts</h3>
        <p class="create-ad-link"><strong><a href="create_advert">Add advert</a></strong></p>
        <table id="adverts-table" class="display">
            <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Post Date</th>
                <th>Model</th>
                <th>Year</th>
                <th>Engine Type</th>
                <th>Engine Volume</th>
                <th>Mileage</th>
                <th>Color</th>
                <th>Comment</th>
                <th>Photo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="advert" items="${moderatedAdverts}">
                <tr class="clickable-row" data-href="show_advert?id=${advert.id}">
                    <td>${advert.id}</td>
                    <td>${advert.user.email}</td>
                    <td><fmt:formatDate value="${advert.postDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
                    <td>${advert.model.brand.name} ${advert.model.name}</td>
                    <td><fmt:formatDate value="${advert.year}" pattern="yyyy"/></td>
                    <td>${advert.engineType.name}</td>
                    <td>${advert.engineVolume}</td>
                    <td>${advert.mileage}</td>
                    <td bgcolor="${advert.color}"></td>
                    <td>${advert.comment}</td>
                    <td>
                        <c:forEach var="photo" begin="0" end="0" items="${advert.photos}">
                            <img src="print_image?id=${photo.id}" class="preview">
                        </c:forEach>
                    </td>
                        <%--<td><a href="show_advert?id=${advert.id}">Show details</a></td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/adverts.js"></script>
</body>
</html>
