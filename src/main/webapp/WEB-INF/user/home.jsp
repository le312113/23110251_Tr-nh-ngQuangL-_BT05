<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Trang Home</title>
</head>
<body>
<!-- Thanh header -->
<div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
    <h2>Trang Home</h2>

    <!-- Hiển thị username và nút đăng xuất -->
    <div>
        <span>Xin chào, ${sessionScope.user.userName} </span>
        <form action="${pageContext.request.contextPath}/logout" method="get" style="display: inline;">
            <button type="submit">Đăng xuất</button>
        </form>
    </div>
</div>

<!-- Các nút điều hướng -->
<div style="margin-top: 20px;">
    <form action="${pageContext.request.contextPath}/list" method="get" style="display:inline;">
        <input type="hidden" name="id" value="${sessionScope.user.id}">
        <button type="submit">Xem danh sách Category</button>
    </form>

    <form action="${pageContext.request.contextPath}/add" method="get" style="display:inline;">
        <input type="hidden" name="id" value="${sessionScope.user.id}">
        <button type="submit">Thêm Category</button>
    </form>
</div>
</body>
</html>
