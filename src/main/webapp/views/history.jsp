<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<h3 class="mb-4">📜 Quán đã xem gần đây</h3>

<c:choose>
  <c:when test="${empty list}">
    <div class="alert alert-info">
      Bạn chưa xem quán nào.
    </div>
  </c:when>

  <c:otherwise>
    <div class="row g-4">
      <c:forEach var="h" items="${list}">
        <div class="col-md-4">
          <div class="card h-100 shadow-sm">

            <!-- POSTER -->
            <img src="${pageContext.request.contextPath}/images/${h.restaurant.posterUrl}"
                 class="card-img-top"
                 alt="${h.restaurant.name}"
                 style="height:160px; object-fit:cover;">

            <!-- BODY -->
            <div class="card-body">
              <h5 class="card-title">
                  ${h.restaurant.name}
              </h5>

              <p class="card-text text-muted mb-1">
                👁 Tổng lượt xem: ${h.restaurant.viewCount}
              </p>

              <p class="card-text text-muted">
                ⏱ Đã xem lúc: ${h.viewedAtFormatted}

              </p>
            </div>

            <!-- ACTION -->
            <div class="card-footer text-center">
              <a href="${pageContext.request.contextPath}/restaurant/detail?id=${h.restaurant.restaurantId}"
                 class="btn btn-sm btn-primary">
                Xem lại
              </a>
            </div>

          </div>
        </div>
      </c:forEach>
    </div>
  </c:otherwise>
</c:choose>
