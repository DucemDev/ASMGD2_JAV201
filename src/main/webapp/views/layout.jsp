<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Owl Review</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
          rel="stylesheet">
</head>
<body>

<!-- ===== HEADER ===== -->
<nav class="navbar navbar-dark bg-dark px-4">
  <span class="navbar-brand fw-bold text-warning">
    🦉 Owl Review
  </span>

    <c:if test="${not empty sessionScope.authUser}">
        <p class="text-white mb-0 me-3">
            Xin chào ${sessionScope.authUser.username}
        </p>
    </c:if>


    <div class="ms-auto d-flex align-items-center">

        <!-- SEARCH -->
        <form class="d-flex me-3"
              method="get"
              action="${pageContext.request.contextPath}/home">
            <input class="form-control form-control-sm me-2"
                   type="search"
                   name="keyword"
                   placeholder="Tìm quán ăn...">
            <button class="btn btn-outline-light btn-sm">
                <i class="bi bi-search"></i>
            </button>
        </form>

        <!-- LOGIN ICON -->
        <a href="${pageContext.request.contextPath}/login"
           class="text-white text-decoration-none">
            <i class="bi bi-person-circle fs-4"></i>
        </a>

    </div>
</nav>

<!-- ===== BODY ===== -->
<div class="container-fluid">
    <div class="row min-vh-100">

        <!-- MENU TRÁI -->
        <div class="col-2 bg-dark text-white p-3">
            <h5 class="text-warning">MENU</h5>

            <a href="${pageContext.request.contextPath}/home"
               class="text-white d-block my-2 text-decoration-none">
                🏠 Trang chủ
            </a>

            <a href="${pageContext.request.contextPath}/favorite"
               class="text-white d-block my-2 text-decoration-none">
                ❤️ Yêu thích
            </a>

            <a href="${pageContext.request.contextPath}/history"
               class="text-white d-block my-2 text-decoration-none">
                🕒 Đã xem
            </a>
            <a href="${pageContext.request.contextPath}/setting"
               class="text-white d-block my-2 text-decoration-none">
                 Cài đặt tài khoản
            </a>
        </div>

        <!-- CONTENT -->
        <div class="col-10 p-4 bg-light">
            <jsp:include page="${contentPage}"/>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
