package controller;

import dao.SharesDAO;
import dao.SharesImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String restaurantId = req.getParameter("id");

        req.setAttribute("restaurantId", restaurantId);
        req.setAttribute("contentPage", "share.jsp");

        req.getRequestDispatcher("/views/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String restaurantId = req.getParameter("restaurantId");
        String email = req.getParameter("email");

        // TẠM GIẢ LẬP USER (CHƯA LOGIN)
        String userId = "u1";

        SharesDAO dao = new SharesImpl();
        dao.share(userId, restaurantId, email);

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
