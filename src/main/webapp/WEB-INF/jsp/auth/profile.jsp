<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/lib/jquery/jquery-3.1.1.min.js"></script>
</head>
<body>
<div id="container">
    <div id="header">
        <h1><a href="show_adverts">Auto Adverts</a></h1>
        <c:if test="${not empty user}">
            <p><strong><a href="logout">Log Out</a></strong></p>
        </c:if>
    </div>
    <div id="content">
        <div>
            <h3>Your profile information</h3>
            <form action="update_user" method="post" id="auth-form">
                <div>
                    <c:choose>
                        <c:when test="${user.role.name eq 'admin'}">
                            <p>You have <b>administrator</b> permissions</p>
                        </c:when>
                        <c:when test="${user.role.name eq 'moderator'}">
                            <p>You have <b>moderator</b> permissions</p>
                        </c:when>
                    </c:choose>
                </div>
                <div>
                    <label for="identity">User ID:</label>
                    <input type="text" id="identity" value="${user.id}" disabled>
                </div>
                <div>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email}" disabled>
                </div>
                <div>
                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" value="${user.phone}" required>
                </div>
                <div>
                    <label for="city">City:</label>
                    <input type="text" id="city" name="city" value="${user.city}" required>
                </div>
                <div>
                    <input type="submit" id="save-btn" value="Save">
                </div>
                <div>
                    <input type="button" id="change-pass-btn" value="Change password">
                </div>
            </form>
            <form action="update_user" method="post" id="change-pass-form">
                <div>
                    <label for="pass">New password:</label>
                    <input type="password" name="pass" id="pass" required>
                </div>
                <div>
                    <label for="sPass">Re-enter password:</label>
                    <input type="password" id="sPass" required>
                </div>
                <div>
                    <input type="submit" value="Change">
                </div>
                <div>
                    <input type="reset" value="Cancel" id="change-pass-cancel">
                </div>
            </form>
        </div>
        <div>
            <h3>Your adverts</h3>
            <c:if test="${empty user.adverts}">
                <p class="no-ads">You don't have adverts yet.</p>
            </c:if>
            <c:if test="${not empty user.adverts}">
                <table class="table-ads">
                    <tr>
                        <th>Advert Id</th>
                        <th>Advert Post Date</th>
                        <th>Advert Brand</th>
                        <th>Advert Model</th>
                        <th>Advert Year</th>
                        <th>Advert Engine Type</th>
                        <th>Advert Engine Volume</th>
                        <th>Advert Mileage</th>
                        <th>Advert Color</th>
                        <th>Advert Price</th>
                        <th>Advert Comment</th>
                        <th>Advert Photos</th>
                        <th>Advert isModerated</th>
                        <th></th>
                    </tr>
                    <c:forEach var="advert" items="${user.adverts}">
                        <tr class="clickable-row" data-href="show_advert?id=${advert.id}">
                            <td>${advert.id}</td>
                            <td>${advert.postDate}</td>
                            <td>${advert.model.brand.name}</td>
                            <td>${advert.model.name}</td>
                            <td>${advert.year}</td>
                            <td>${advert.engineType.name}</td>
                            <td>${advert.engineVolume}</td>
                            <td>${advert.mileage}</td>
                            <td>${advert.color}</td>
                            <td>${advert.price}</td>
                            <td>${advert.comment}</td>
                            <td>
                                <c:forEach var="photo" items="${advert.photos}">
                                    <img src="print_image?id=${photo.id}" class="preview">
                                </c:forEach>
                            </td>
                            <td>${advert.moderated}</td>
                            <td><a href="delete_advert?id=${advert.id}"><span class="deny">delete</span></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <c:if test="${user.role.name eq 'moderator' || user.role.name eq 'admin'}">
            <div>
                <h3>Moderation</h3>
                <c:if test="${empty notModeratedAdverts}">
                    <p class="no-ads">You don't have not moderated adverts</p>
                </c:if>
                <c:if test="${not empty notModeratedAdverts}">
                    <table class="table-ads">
                        <tr>
                            <th>Advert Id</th>
                            <th>Advert Post Date</th>
                            <th>Advert Brand</th>
                            <th>Advert Model</th>
                            <th>Advert Year</th>
                            <th>Advert Engine Type</th>
                            <th>Advert Engine Volume</th>
                            <th>Advert Mileage</th>
                            <th>Advert Color</th>
                            <th>Advert Price</th>
                            <th>Advert Comment</th>
                            <th>Advert Photos</th>
                            <th>Advert isModerated</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="advert" items="${notModeratedAdverts}">
                            <tr class="clickable-row" data-href="show_advert?id=${advert.id}">
                                <td>${advert.id}</td>
                                <td>${advert.postDate}</td>
                                <td>${advert.model.brand.name}</td>
                                <td>${advert.model.name}</td>
                                <td>${advert.year}</td>
                                <td>${advert.engineType.name}</td>
                                <td>${advert.engineVolume}</td>
                                <td>${advert.mileage}</td>
                                <td>${advert.color}</td>
                                <td>${advert.price}</td>
                                <td>${advert.comment}</td>
                                <td>
                                    <c:forEach var="photo" items="${advert.photos}">
                                        <img src="print_image?id=${photo.id}" class="preview">
                                    </c:forEach>
                                </td>
                                <td>${advert.moderated}</td>
                                <td><a href="update_advert?id=${advert.id}"><span class="allow">allow</span></a></td>
                                <td><a href="delete_advert?id=${advert.id}"><span class="deny">deny</span></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </c:if>
        <c:if test="${user.role.name eq 'admin'}">
            <div>
                <h3>Administration</h3>
                <h4><a href="admin_area">Users</a></h4>
                <h4><a href="auto_catalog">Auto Catalog</a></h4>
            </div>
        </c:if>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>
</body>
</html>
