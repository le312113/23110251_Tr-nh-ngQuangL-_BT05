<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<p>DEBUG JSP cateList size = ${fn:length(cateList)}</p>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Ảnh</th>
        <th>Tên</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cateList}" var="cate" varStatus="st">
        <tr class="odd gradeX">
            <td>${st.index + 1}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty cate.icons}">
                        <c:url value="/image" var="imgUrl">
                            <c:param name="fname" value="${cate.icons}"/>
                        </c:url>
                        <img src="${imgUrl}" width="200" height="150" alt="${cate.cate_name}" />
                    </c:when>
                    <c:otherwise>
                        <span>Không có ảnh</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${cate.cate_name}</td>
            <td class="center">
                <a href="<c:url value='/edit?id=${cate.cate_id}'/>">Sửa</a> |
                <form method="post" action="<c:url value='/delete'/>" style="display:inline;">
                    <input type="hidden" name="id" value="${cate.cate_id}">
                    <button type="submit" onclick="return confirm('Xóa danh mục này?');">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
