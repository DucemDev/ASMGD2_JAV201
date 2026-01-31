<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<h3 class="mb-4">Danh sách quán ăn</h3>


<c:choose>
    <c:when test="${empty list}">
        <div class="alert alert-warning">
            Không có dữ liệu quán ăn.
        </div>
    </c:when>

    <c:otherwise>
        <div class="row g-4">
            <c:forEach var="r" items="${list}">
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm">

                        <!-- POSTER -->
                        <img src="${pageContext.request.contextPath}/images/${r.posterUrl}"
                             class="card-img-top"
                             alt="${r.name}"
                             style="height:160px; object-fit:cover;">

                        <!-- BODY -->
                        <div class="card-body">
                            <h5 class="card-title">${r.name}</h5>
                            <p class="card-text">
                                👁 ${r.viewCount} lượt xem
                            </p>
                        </div>

                        <!-- ACTIONS -->
                        <div class="card-footer text-center">

                            <a href="${pageContext.request.contextPath}/restaurant/detail?id=${r.restaurantId}"
                               class="btn btn-primary btn-sm">
                                👁 Xem chi tiết
                            </a>

                            <!-- LIKE → CHƯA LOGIN → ĐẨY QUA LOGIN -->
                            <a href="${pageContext.request.contextPath}/login"
                               class="btn btn-outline-danger btn-sm ms-1">
                                ❤️ Like
                            </a>

                            <!-- SHARE → CHƯA LOGIN → ĐẨY QUA LOGIN -->
                            <a href="${pageContext.request.contextPath}/login"
                               class="btn btn-outline-secondary btn-sm ms-1">
                                📤 Share
                            </a>

                        </div>


                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- PHÂN TRANG -->
        <nav class="mt-4">
            <ul class="pagination justify-content-center">

                <c:if test="${page > 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/home?page=${page - 1}">
                            &laquo;
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == page ? 'active' : ''}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/home?page=${i}">
                                ${i}
                        </a>
                    </li>
                </c:forEach>

                <c:if test="${page < totalPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/home?page=${page + 1}">
                            &raquo;
                        </a>
                    </li>
                </c:if>

            </ul>
        </nav>
    </c:otherwise>
</c:choose>
