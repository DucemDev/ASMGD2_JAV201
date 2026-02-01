<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý Nhà hàng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .preview-box { width: 100%; height: 200px; object-fit: cover; border: 1px dashed #ccc; display: block; }
    .video-container { position: relative; padding-bottom: 56.25%; height: 0; overflow: hidden; }
    .video-container iframe { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
  </style>
</head>
<body class="container py-4">
<c:if test="${empty sessionScope.authUser || !sessionScope.authUser.role}">
  <c:redirect url="/login"/>
</c:if>
<h2 class="mb-4">⚙️ Quản lý Nhà hàng</h2>

<div class="row">
  <div class="col-md-7">
    <div class="card shadow-sm mb-4">
      <div class="card-body">
        <form action="${pageContext.request.contextPath}/restaurant/${not empty form.restaurantId ? 'update' : 'create'}" method="post">
          <div class="row">
            <div class="col-md-4 mb-3">
              <label class="form-label">Mã quán</label>
              <input name="id" value="${form.restaurantId}" class="form-control" ${not empty form.restaurantId ? 'readonly' : ''} required>
            </div>
            <div class="col-md-8 mb-3">
              <label class="form-label">Tên nhà hàng</label>
              <input name="name" value="${form.name}" class="form-control" required>
            </div>
            <div class="col-md-12 mb-3">
              <label class="form-label">Link Poster (Ảnh)</label>
              <input name="poster" id="posterUrl" value="${form.posterUrl}" class="form-control" placeholder="https://...">
            </div>
            <div class="col-md-12 mb-3">
              <label class="form-label">Link Video (YouTube)</label>
              <input name="video" id="videoUrl" value="${form.videoUrl}" class="form-control" placeholder="https://www.youtube.com/embed/...">
            </div>
          </div>
          <button type="submit" class="btn btn-primary">${not empty form.restaurantId ? 'Cập nhật' : 'Thêm mới'}</button>
          <a href="${pageContext.request.contextPath}/restaurant/reset" class="btn btn-outline-secondary">Làm mới</a>
        </form>
      </div>
    </div>
  </div>

  <div class="col-md-5">
    <div class="card shadow-sm">
      <div class="card-header">Xem trước Media</div>
      <div class="card-body text-center">
        <img id="imgPreview" src="${not empty form.posterUrl ? form.posterUrl : 'https://placehold.co/600x400?text=No+Image'}" class="preview-box mb-3">
        <div class="video-container">
          <iframe id="videoPreview" src="${not empty form.videoUrl ? form.videoUrl : ''}" frameborder="0" allowfullscreen></iframe>
        </div>
      </div>
    </div>
  </div>
</div>

<table class="table table-hover mt-4 border shadow-sm">
  <thead class="table-dark">
  <tr>
    <th>Mã</th> <th>Tên</th> <th>Hình ảnh</th> <th>View</th> <th>Thao tác</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="item" items="${items}">
    <tr>
      <td>${item.restaurantId}</td>
      <td><strong>${item.name}</strong></td>
      <td><img src="${item.posterUrl}" width="80" class="rounded shadow-sm"></td>
      <td>${item.viewCount}</td>
      <td>
        <a href="${pageContext.request.contextPath}/admin/restaurants/edit/${r.restaurantId}"
           class="btn btn-sm btn-warning">
          Sửa
        </a>


        <a href="${pageContext.request.contextPath}/admin/restaurants/delete/${r.restaurantId}"
           class="btn btn-sm btn-danger"
           onclick="return confirm('Bạn chắc chắn muốn xóa?')">
          Xóa
        </a>


      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<script>
  document.getElementById('posterUrl').addEventListener('input', function() {
    document.getElementById('imgPreview').src = this.value || 'https://placehold.co/600x400?text=No+Image';
  });
  document.getElementById('videoUrl').addEventListener('input', function() {
    document.getElementById('videoPreview').src = this.value;
  });
</script>
</body>
</html>