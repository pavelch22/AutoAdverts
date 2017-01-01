<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auto Catalog</title>
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
        <h4 class="h-line">Models</h4>
        <div class="tab">
            <div id="brands-container">
                <label for="brands">Brands:</label>
                <select id="brands"></select>
                <div id="brand-controls">
                    <button id="add-brand">Add</button>
                    <button id="update-brand" disabled>Update</button>
                    <button id="delete-brand" disabled>Delete</button>
                </div>
            </div>
            <form id="brand-form" action="" method="post">
                <label for="b-identity">Id:</label>
                <input type="text" name="id" id="b-identity" readonly>
                <label for="brand-name">Brand:</label>
                <input type="text" name="brandName" id="brand-name" required>
                <input type="submit" value="Save">
                <input type="reset" value="Cancel" id="brand-cancel">
            </form>
            <table id="models" class="table-ads">
                <tr>
                    <th>Id</th>
                    <th>Model</th>
                    <th></th>
                </tr>
                <tr>
                    <td colspan="2">Not found...</td>
                </tr>
            </table>
            <div id="model-controls" class="controls">
                <button id="add-model">Add</button>
                <button id="update-model" disabled>Update</button>
                <button id="delete-model" disabled>Delete</button>
            </div>
            <form id="model-form" action="" method="post">
                <label for="m-identity">Id:</label>
                <input type="text" name="id" id="m-identity" readonly>
                <label for="model-name">Model:</label>
                <input type="text" name="modelName" id="model-name" required>
                <input type="submit" value="Save">
                <input type="reset" value="Cancel" id="model-cancel">
            </form>
        </div>
        <h4 class="h-line">Engine Types</h4>
        <div class="tab">
            <table id="engine-types" class="table-ads">
                <tr>
                    <th>Id</th>
                    <th>Engine Type</th>
                    <th></th>
                </tr>
            </table>
            <div id="engine-controls" class="controls">
                <button id="add-engine">Add</button>
                <button id="update-engine" disabled>Update</button>
                <button id="delete-engine" disabled>Delete</button>
            </div>
            <form id="engine-form" action="" method="post">
                <label for="en-identity">Id:</label>
                <input type="text" name="id" id="en-identity" readonly>
                <label for="engine-type">Engine Type</label>
                <input type="text" name="engineType" id="engine-type" required>
                <input type="submit" value="Save">
                <input type="reset" value="Cancel" id="engine-cancel">
            </form>
        </div>
        <p class="go-back"><a href="profile">Go Back</a></p>
    </div>
    <div id="footer">
        <p>Copyright&copy;2016</p>
        <p>Contact us:<a href="mailto:${initParam.email}">${initParam.email}</a></p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/autocatalog.js"></script>
</body>
</html>
