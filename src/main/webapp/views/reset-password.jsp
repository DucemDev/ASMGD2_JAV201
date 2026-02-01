<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="container mt-4">

    <div class="row justify-content-center">
        <div class="col-lg-5 col-md-7">

            <div class="card shadow-sm border-0">

                <div class="card-header bg-white text-center fw-semibold">
                    Đặt lại mật khẩu
                </div>

                <div class="card-body p-4">

                    <form method="post"
                          action="<c:url value='/reset-password'/>">

                        <div class="mb-3">
                            <label class="form-label">Mã OTP</label>
                            <input type="text"
                                   name="otp"
                                   class="form-control"
                                   placeholder="Nhập mã OTP đã nhận"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Mật khẩu mới</label>
                            <input type="password"
                                   name="password"
                                   class="form-control"
                                   placeholder="Nhập mật khẩu mới"
                                   required>
                        </div>

                        <button class="btn btn-success w-100 mt-2">
                            Xác nhận đổi mật khẩu
                        </button>
                    </form>

                    <c:if test="${not empty message}">
                        <div class="alert alert-danger mt-3 text-center">
                                ${message}
                        </div>
                    </c:if>

                </div>

                <div class="card-footer bg-white text-center">
                    <a href="<c:url value='/login'/>"
                       class="text-decoration-none">
                        Quay lại đăng nhập
                    </a>
                </div>

            </div>

        </div>
    </div>

</div>
