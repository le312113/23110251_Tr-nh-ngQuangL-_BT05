<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Admin - <sitemesh:write property="title"/></title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<!-- Header chung -->
<nav class="navbar navbar-dark bg-dark p-3">
  <a class="navbar-brand" href="#">Admin Panel</a>
  <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</nav>

<div class="container-fluid mt-3">
  <!-- Nội dung trang admin sẽ hiển thị ở đây -->
  <sitemesh:write property="body"/>
</div>
</body>
</html>
