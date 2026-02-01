package controller;

import dao.UsersDAO;
import dao.UsersImpl;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/users/index",
        "/admin/users/edit/*",
        "/admin/users/create",
        "/admin/users/update",
        "/admin/users/delete/*",
        "/admin/users/reset"
})
public class UserServlet extends HttpServlet {
    private UsersDAO dao = new UsersImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("edit")) {
            String id = req.getPathInfo().substring(1); // Lấy ID từ URL /edit/username
            Users user = dao.findById(id);
            req.setAttribute("form", user);
        } else if (uri.contains("delete")) {
            String id = req.getPathInfo().substring(1);
            dao.delete(id);
            req.setAttribute("message", "Xóa người dùng thành công!");
        } else if (uri.contains("reset")) {
            req.setAttribute("form", new Users());
        }

        this.findAll(req, resp);
        req.getRequestDispatcher("/views/admin/user-manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Users user = new Users();

        try {
            // Tự động đổ dữ liệu từ form vào object User
            BeanUtils.populate(user, req.getParameterMap());

            if (uri.contains("create")) {
                dao.create(user);
                req.setAttribute("message", "Thêm mới thành công!");
            } else if (uri.contains("update")) {
                dao.update(user);
                req.setAttribute("message", "Cập nhật thành công!");
            }
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi xử lý dữ liệu: " + e.getMessage());
        }

        this.findAll(req, resp);
        req.getRequestDispatcher("/views/admin/user-manager.jsp").forward(req, resp);
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) {
        List<Users> list = dao.findAll();
        req.setAttribute("items", list);
    }
}