<%--
  Created by IntelliJ IDEA.
  User: sbt09
  Date: 2/1/2026
  Time: 2:57 AM
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>


<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>${r.name}</h2>
<p>Lượt xem: ${r.viewCount}</p>

<iframe width="560" height="315" src="${r.videoUrl}"></iframe>

<form method="post" action="${pageContext.request.contextPath}/restaurant/like">
    <input type="hidden" name="id" value="${r.restaurantId}">
    <button>Like</button>
</form>

</body>
</html>
