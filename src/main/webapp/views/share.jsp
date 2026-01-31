<%@ page contentType="text/html; charset=UTF-8" %>

<h3 class="mb-4">📤 Chia sẻ quán ăn</h3>

<form method="post"
      action="${pageContext.request.contextPath}/share"
      class="col-md-6">

    <input type="hidden" name="restaurantId"
           value="${restaurantId}">

    <div class="mb-3">
        <label class="form-label">Email người nhận</label>
        <input type="email"
               name="email"
               class="form-control"
               required
               placeholder="example@gmail.com">
    </div>

    <button class="btn btn-primary">
        Gửi chia sẻ
    </button>

    <a href="${pageContext.request.contextPath}/home"
       class="btn btn-secondary ms-2">
        Huỷ
    </a>
</form>
