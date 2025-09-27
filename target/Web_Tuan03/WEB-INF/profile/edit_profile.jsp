<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sửa Profile</title>
</head>
<body>
<h2>Sửa Profile</h2>

<form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="id" value="${user.id}">

    <label>Full Name:</label>
    <input type="text" name="fullName" value="${user.fullName}" required><br><br>

    <label>Phone:</label>
    <input type="text" name="phone" value="${user.phone}" required><br><br>

    <label>Avatar:</label>
    <c:if test="${not empty user.avatar}">
        <img src="${pageContext.request.contextPath}/uploads/${user.avatar}" alt="Avatar" width="60"><br>
    </c:if>
    <input type="file" name="avatar"><br><br>

    <button type="submit">Cập nhật Profile</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/profile?action=list">Quay về danh sách</a>
</body>
</html>
