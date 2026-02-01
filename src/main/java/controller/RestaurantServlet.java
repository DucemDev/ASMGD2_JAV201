package controller;

import dao.RestaurantDAO;
import dao.RestaurantImpl;
import entity.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/restaurant/index", "/restaurant/create", "/restaurant/edit", "/restaurant/update", "/restaurant/delete", "/restaurant/reset"})
public class RestaurantServlet extends HttpServlet {
    RestaurantDAO dao = new RestaurantImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("edit")) {
            String id = req.getParameter("id");
            req.setAttribute("form", dao.findbyid(id));
        } else if (uri.contains("delete")) {
            dao.delete(req.getParameter("id"));
        } else if (uri.contains("reset")) {
            req.setAttribute("form", new Restaurant());
        }

        List<Restaurant> list = dao.findall();
        req.setAttribute("items", list);
        req.getRequestDispatcher("/views/admin/restaurant-manager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        // Tạo đối tượng từ tham số form
        Restaurant r = new Restaurant();
        r.setRestaurantId(req.getParameter("id"));
        r.setName(req.getParameter("name"));
        r.setPosterUrl(req.getParameter("poster"));
        r.setVideoUrl(req.getParameter("video"));

        if (uri.contains("create")) {
            r.setViewCount(0);
            dao.create(r);
        } else if (uri.contains("update")) {
            // Lấy lại viewCount cũ từ DB để không bị mất khi update
            Restaurant old = dao.findbyid(r.getRestaurantId());
            r.setViewCount(old != null ? old.getViewCount() : 0);
            dao.update(r);
        }

        resp.sendRedirect(req.getContextPath() + "/restaurant/index");
    }
}