package controller;

import dao.SharesDAO;
import dao.SharesImpl;
import entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/share")
public class ShareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        // truyền restaurantId sang JSP
        req.setAttribute("restaurantId", idParam);
        req.setAttribute("contentPage", "/views/share.jsp");

        req.getRequestDispatcher("/views/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users user = (Users) req.getSession().getAttribute("authUser");
        Integer userId = user.getUserId();

        Integer restaurantId =
                Integer.parseInt(req.getParameter("restaurantId"));

        String email = req.getParameter("email");

        SharesDAO dao = new SharesImpl();
        dao.share(userId, restaurantId, email);

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
