<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Đăng nhập - Owl Review</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-md-4">

      <div class="card shadow">
        <div class="card-header text-center fw-bold">
          🦉 Đăng nhập
        </div>

        <div class="card-body">
          <form>
            <div class="mb-3">
              <label class="form-label">Email</label>
              <input type="email" class="form-control"
                     placeholder="Nhập email">
            </div>

            <div class="mb-3">
              <label class="form-label">Mật khẩu</label>
              <input type="password" class="form-control"
                     placeholder="Nhập mật khẩu">
            </div>

            <button type="button"
                    class="btn btn-primary w-100">
              Đăng nhập
            </button>
          </form>
        </div>

        <div class="card-footer text-center text-muted">
          (Chưa xử lý logic)
        </div>
      </div>

    </div>
  </div>
</div>

</body>
</html>
