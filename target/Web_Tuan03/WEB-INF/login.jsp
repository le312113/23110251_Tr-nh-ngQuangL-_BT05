<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Đăng nhập</h2>

<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

<form method="post" action="<c:url value='/login'/>">
    <label>Username:</label>
    <input type="text" name="username" required />
    <br/>
    <label>Password:</label>
    <input type="password" name="password" required />
    <br/>
    <button type="submit">Login</button>
</form>

</body>
</html>
