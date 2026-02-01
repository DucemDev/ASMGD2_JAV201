<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Báo Cáo Quản Trị</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-4">
	<div class="d-flex justify-content-between align-items-center mb-4">
		<h2 class="text-primary fw-bold">BÁO CÁO HỆ THỐNG</h2>
		<a href="${pageContext.request.contextPath}/admin/reports" class="btn btn-outline-secondary">Làm mới dữ liệu</a>
	</div>

	<div class="row">
		<div class="col-md-4">
			<div class="card shadow-sm border-0">
				<div class="card-header bg-danger text-white fw-bold">Lượt Like Theo Quán</div>
				<div class="card-body p-0">
					<table class="table table-hover mb-0">
						<thead>
						<tr>
							<th>Tên nhà hàng</th>
							<th class="text-center">Số Like</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach var="stat" items="${likeStats}">
							<tr>
								<td>${stat[0]}</td>
								<td class="text-center"><span class="badge bg-danger rounded-pill">${stat[1]}</span></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="col-md-8">
			<div class="card shadow-sm border-0 mb-4">
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/admin/reports" method="get" class="row g-2">
						<div class="col-9">
							<select name="restaurantId" class="form-select border-primary">
								<option value="">-- Chọn nhà hàng để xem chi tiết --</option>
								<c:forEach var="r" items="${allRestaurants}">
									<option value="${r.restaurantId}" ${param.restaurantId == r.restaurantId ? 'selected' : ''}>
											${r.name}
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-3">
							<button type="submit" class="btn btn-primary w-100">Xem Báo Cáo</button>
						</div>
					</form>
				</div>
			</div>

			<c:if test="${not empty selectedRest}">
				<div class="row g-3">
					<div class="col-6">
						<div class="card border-0 shadow-sm h-100">
							<div class="card-header bg-success text-white small fw-bold">Users Đã Like</div>
							<ul class="list-group list-group-flush">
								<c:forEach var="user" items="${likedUsers}">
									<li class="list-group-item small">${user.fullname} <span class="text-muted">(${user.userId})</span></li>
								</c:forEach>
								<c:if test="${empty likedUsers}">
									<li class="list-group-item text-center text-muted py-3 small">Chưa có ai like quán này</li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="col-6">
						<div class="card border-0 shadow-sm h-100">
							<div class="card-header bg-info text-white small fw-bold">Emails Nhận Chia Sẻ</div>
							<ul class="list-group list-group-flush">
								<c:forEach var="mail" items="${sharedEmails}">
									<li class="list-group-item small text-primary">${mail}</li>
								</c:forEach>
								<c:if test="${empty sharedEmails}">
									<li class="list-group-item text-center text-muted py-3 small">Chưa có lượt chia sẻ nào</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>