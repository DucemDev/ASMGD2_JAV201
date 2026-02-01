package controller;

import dao.*;
import entity.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/reports")
public class ReportServlet extends HttpServlet {
    private ReportDAO reportDao = new ReportImpl();
    private RestaurantDAO restDao = new RestaurantImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Restaurant> list = restDao.findall();
            req.setAttribute("allRestaurants", list != null ? list : new ArrayList<>());

            String restaurantId = req.getParameter("restaurantId");

            if (restaurantId != null && !restaurantId.isEmpty()) {
                Restaurant selected = restDao.findbyid(restaurantId);

                if (selected != null) {
                    req.setAttribute("selectedRest", selected);

                    // Lấy dữ liệu từ DAO
                    List<?> liked = reportDao.findUsersByLikedRestaurant(restaurantId);
                    List<?> shared = reportDao.findEmailsBySharedRestaurant(restaurantId);
                    List<?> comms = reportDao.findCommentsByRestaurant(restaurantId);

                    // Đưa danh sách vào attribute
                    req.setAttribute("likedUsers", liked != null ? liked : new ArrayList<>());
                    req.setAttribute("sharedEmails", shared != null ? shared : new ArrayList<>());
                    req.setAttribute("comments", comms != null ? comms : new ArrayList<>());

                    // Đếm tổng số lượng để hiển thị ở góc phải các thanh màu
                    req.setAttribute("countLikes", liked != null ? liked.size() : 0);
                    req.setAttribute("countShares", shared != null ? shared.size() : 0);
                    req.setAttribute("countComms", comms != null ? comms.size() : 0);
                }
            }

            req.getRequestDispatcher("/views/admin/reports.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Loi lay du lieu bao cao: " + e.getMessage());
        }
    }
}