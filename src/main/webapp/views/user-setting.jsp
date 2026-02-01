<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cài đặt tài khoản</title>
</head>
<body>

<h2>⚙️ Cài đặt tài khoản</h2>

<p>Xin chào <b>${sessionScope.authUser.username}</b></p>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

<hr>

<!-- ===== ĐỔI MẬT KHẨU ===== -->
<h3>🔑 Đổi mật khẩu</h3>
<form method="post"
      action="${pageContext.request.contextPath}/setting">

    <input type="hidden" name="action" value="changePassword"/>

    <input type="password"
           name="oldPassword"
           placeholder="Mật khẩu cũ"
           required><br><br>

    <input type="password"
           name="newPassword"
           placeholder="Mật khẩu mới"
           required><br><br>

    <button type="submit">Đổi mật khẩu</button>
</form>

<hr>

<!-- ===== CẬP NHẬT THÔNG TIN ===== -->
<h3>✏️ Thông tin cá nhân</h3>
<form method="post"
      action="${pageContext.request.contextPath}/setting">

    <input type="hidden" name="action" value="updateInfo"/>

    <input type="text"
           name="username"
           value="${sessionScope.authUser.username}"
           required>

    <button type="submit">Lưu</button>
</form>

<hr>

<!-- ===== LOGOUT ===== -->
<a href="${pageContext.request.contextPath}/setting?action=logout">
    🚪 Đăng xuất
</a>

</body>
</html>
