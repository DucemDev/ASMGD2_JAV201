package controller;

import dao.RestaurantDAO;
import dao.RestaurantImpl;
import entity.Restaurant;
import entity.ViewHistory;
import entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import util.XJPA;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet("/restaurant/detail")
public class RestaurantDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        RestaurantDAO dao = new RestaurantImpl();
        Restaurant restaurant = dao.findbyid(id);

        if (restaurant == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // 1️⃣ TĂNG VIEW COUNT
        restaurant.setViewCount(restaurant.getViewCount() + 1);
        dao.update(restaurant);

        // 2️⃣ LƯU VIEW HISTORY (GIẢ LẬP USER)
        EntityManager em = XJPA.getEntityManager();
        try {
            em.getTransaction().begin();

            ViewHistory vh = new ViewHistory();
            vh.setHistoryId(UUID.randomUUID().toString());
            vh.setRestaurant(restaurant);
            vh.setViewedAt(LocalDateTime.now());



            // giả lập user u1
            Users user = em.find(Users.class, "u1");
            vh.setUser(user);

            em.persist(vh);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        // 3️⃣ GỬI DATA QUA JSP
        req.setAttribute("restaurant", restaurant);
        req.setAttribute("contentPage", "/views/detail.jsp");
        req.getRequestDispatcher("/views/layout.jsp").forward(req, resp);
    }
}
