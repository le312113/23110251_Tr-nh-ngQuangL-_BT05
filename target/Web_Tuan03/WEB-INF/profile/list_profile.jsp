<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách Profile</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        .actions button {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<h2>Danh sách Profile</h2>

<!-- Nút thêm profile -->
<form action="${pageContext.request.contextPath}/profile" method="get" style="margin-bottom: 20px;">
    <input type="hidden" name="action" value="add">
    <button type="submit">Thêm Profile</button>
</form>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Phone</th>
        <th>Image</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${list_user}">
        <tr>
            <td>${user.id}</td>
            <td>${user.fullName}</td>
            <td>${user.phone}</td>
            <td>
                <c:if test="${not empty user.avatar}">
                    <img src="${pageContext.request.contextPath}/uploads/${user.avatar}" alt="Avatar" width="60">
                </c:if>
            </td>
            <td class="actions">
                <!-- Nút sửa -->
                <form action="${pageContext.request.contextPath}/profile" method="get" style="display:inline;">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit">Sửa</button>
                </form>

                <!-- Nút xóa -->
                <form action="${pageContext.request.contextPath}/profile" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" onclick="return confirm('Bạn có chắc muốn xóa không?')">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
