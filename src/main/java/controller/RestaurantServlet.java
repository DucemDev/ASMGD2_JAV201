package controller;

import dao.RestaurantDAO;
import dao.RestaurantImpl;
import entity.Restaurant;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/restaurant",
        "/admin/restaurant/create",
        "/admin/restaurant/update",
        "/admin/restaurant/delete"
})
public class RestaurantServlet extends HttpServlet {

    private final RestaurantDAO dao = new RestaurantImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ===== CHECK ADMIN =====
        Users admin = (Users) req.getSession().getAttribute("authUser");
        if (admin == null || !admin.isRole()) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String uri = req.getRequestURI();

        // ===== DELETE =====
        if (uri.contains("/delete")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/restaurant");
            return;
        }

        // ===== EDIT =====
        if (uri.contains("/edit")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Restaurant r = dao.findById(id); // ✅ SỬA
            req.setAttribute("form", r);
        } else {
            req.setAttribute("form", new Restaurant());
        }

        // ===== LOAD LIST =====
        List<Restaurant> list = dao.findAll(); // ✅ SỬA
        req.setAttribute("items", list);

        req.getRequestDispatcher("/views/admin/restaurant.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        Integer id = (req.getParameter("id") != null && !req.getParameter("id").isEmpty())
                ? Integer.parseInt(req.getParameter("id"))
                : null;

        Restaurant r = new Restaurant();
        r.setRestaurantId(id);
        r.setName(req.getParameter("name"));
        r.setPosterUrl(req.getParameter("poster"));
        r.setVideoUrl(req.getParameter("video"));

        if (uri.contains("/create")) {
            r.setViewCount(0);
            dao.create(r);
        } else if (uri.contains("/update")) {
            dao.update(r);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/restaurant");
    }
}
