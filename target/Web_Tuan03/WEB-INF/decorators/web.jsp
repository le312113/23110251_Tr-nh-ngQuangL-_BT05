<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<header>
    <%@ include file="/commons/web/header.jsp" %>
</header>

<main class="container mt-4">
    <sitemesh:write property="body"/>
</main>

<footer class="bg-light p-3 text-center">
    <%@ include file="/commons/web/footer.jsp" %>
</footer>
</body>
</html>
