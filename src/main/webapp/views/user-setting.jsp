<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head></head>
<body>
<ul>
    <li><a href="${pageContext.request.contextPath}/ChangePassword">Đổi mật khẩu</a></li>
    <li>
        <a href="${pageContext.request.contextPath}/ChangeInformation">Chỉnh sửa thong tin cá nhân</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/Logout">Đăng xuất</a>
    </li>
</ul>
</body>
</html>
