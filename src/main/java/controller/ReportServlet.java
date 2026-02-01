package controller;

import dao.ReportDAO;
import dao.ReportImpl;
import dao.RestaurantDAO;
import dao.RestaurantImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/reports")
public class ReportServlet extends HttpServlet {
    private ReportDAO reportDao = new ReportImpl();
    private RestaurantDAO restDao = new RestaurantImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("likeStats", reportDao.countLikesByRestaurant());

            req.setAttribute("allRestaurants", restDao.findall());

            String restaurantId = req.getParameter("restaurantId");
            if (restaurantId != null && !restaurantId.isEmpty()) {
                req.setAttribute("selectedRest", restDao.findbyid(restaurantId));
                req.setAttribute("likedUsers", reportDao.findUsersByLikedRestaurant(restaurantId));
                req.setAttribute("sharedEmails", reportDao.findEmailsBySharedRestaurant(restaurantId));
            }

            req.getRequestDispatcher("/views/admin/reports.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Loi tai Servlet: " + e.getMessage());
        }
    }
}