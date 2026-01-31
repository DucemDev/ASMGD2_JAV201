<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:forEach var="r" items="${list}">
    <img src="/images/${r.posterUrl}" width="200">

    <h3>${r.name}</h3>
    <p>Lượt xem: ${r.viewCount}</p>

    <a href="${pageContext.request.contextPath}/restaurant/detail?id=${r.restaurantId}">
        Xem chi tiết
    </a>

    <a href="${pageContext.request.contextPath}/like?id=${r.restaurantId}">
        Like
    </a>

    <a href="${pageContext.request.contextPath}/share?id=${r.restaurantId}">
        Share
    </a>

    <hr>
</c:forEach>
