<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<c:url value="/edit" var="editUrl"/>
<c:url value="/image" var="imgUrl">
    <c:param name="fname" value="${category.icons}"/>
</c:url>

<form method="post" action="${editUrl}" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${category.cate_id}"/>
    <input type="hidden" name="oldIcon" value="${category.icons}"/>

    <label>Tên danh mục:</label>
    <input type="text" name="name" value="${category.cate_name}" class="form-control"/>

    <p>Ảnh hiện tại:</p>
    <img src="${imgUrl}" width="120" alt="icon"/>

    <p>Chọn ảnh mới (nếu muốn đổi):</p>
    <input type="file" name="icon"/>

    <button type="submit" class="btn btn-default">Lưu</button>
</form>
