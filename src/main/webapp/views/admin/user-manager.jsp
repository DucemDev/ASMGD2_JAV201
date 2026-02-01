<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quản lý Người dùng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .form-label { font-weight: 500; }
    .table thead { background-color: #f8f9fa; }
  </style>
</head>
<body class="container py-4">
<h2 class="mb-4 text-primary">👥 Quản lý tài khoản người dùng</h2>

<div class="card mb-4 shadow-sm">
  <div class="card-header bg-dark text-white">Thông tin người dùng</div>
  <div class="card-body">
    <form action="${pageContext.request.contextPath}/admin/users/${not empty form.userId ? 'update' : 'create'}" method="post">
      <div class="row">
        <div class="col-md-3 mb-3">
          <label class="form-label">Mã định danh (ID)</label>
          <input name="userId" value="${form.userId}" class="form-control" ${not empty form.userId ? 'readonly' : ''} required>
        </div>

        <div class="col-md-3 mb-3">
          <label class="form-label">Tên tài khoản</label>
          <input name="username" value="${form.username}" class="form-control" required>
        </div>

        <div class="col-md-3 mb-3">
          <label class="form-label">Email</label>
          <input type="email" name="email" value="${form.email}" class="form-control" required>
        </div>

        <div class="col-md-3 mb-3">
          <label class="form-label">Mật khẩu</label>
          <input type="password" name="password" value="${form.password}" class="form-control" required>
        </div>

        <div class="col-md-3 mb-3">
          <label class="form-label">Vai trò</label>
          <div class="mt-2">
            <input type="radio" name="role" value="true" ${form.role ? 'checked' : ''}> Admin
            <input type="radio" name="role" value="false" ${!form.role ? 'checked' : ''} class="ms-2"> User
          </div>
        </div>

        <div class="col-md-3 mb-3">
          <label class="form-label">Trạng thái</label>
          <div class="mt-2">
            <input type="radio" name="status" value="true" ${form.status ? 'checked' : ''}> Hoạt động
            <input type="radio" name="status" value="false" ${!form.status ? 'checked' : ''} class="ms-2"> Khóa
          </div>
        </div>
      </div>

      <div class="mt-3">
        <button type="submit" class="btn btn-primary px-4">
          ${not empty form.userId ? 'Cập nhật' : 'Thêm mới'}
        </button>
        <a href="${pageContext.request.contextPath}/admin/users/reset" class="btn btn-outline-secondary px-4 ms-2">Làm mới</a>
      </div>
    </form>
  </div>
</div>

<div class="card shadow-sm">
  <table class="table table-hover align-middle mb-0">
    <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Tên tài khoản</th>
      <th>Email</th>
      <th>Vai trò</th>
      <th>Trạng thái</th>
      <th class="text-center">Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="u" items="${items}">
      <tr>
        <td class="fw-bold">${u.userId}</td>
        <td>${u.username}</td>
        <td>${u.email}</td>
        <td>
                            <span class="badge ${u.role ? 'bg-info' : 'bg-secondary'}">
                                ${u.role ? 'Quản trị' : 'Người dùng'}
                            </span>
        </td>
        <td>
                            <span class="badge ${u.status ? 'bg-success' : 'bg-danger'}">
                                ${u.status ? 'Kích hoạt' : 'Đã khóa'}
                            </span>
        </td>
        <td class="text-center">
          <a href="${pageContext.request.contextPath}/admin/users/edit/${u.userId}" class="btn btn-sm btn-warning">Sửa</a>
          <a href="${pageContext.request.contextPath}/admin/users/delete/${u.userId}"
             class="btn btn-sm btn-danger"
             onclick="return confirm('Bạn có thực sự muốn xóa người dùng ${u.username}?')">Xóa</a>
        </td>
      </tr>
    </c:forEach>
    <c:if test="${empty items}">
      <tr><td colspan="6" class="text-center p-4 text-muted">Không có người dùng nào được tìm thấy.</td></tr>
    </c:if>
    </tbody>
  </table>
</div>
</body>
</html>