package controller;

import dao.FavoriteDAO;
import dao.FavoriteImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/unlike")
public class UnlikeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String restaurantId = req.getParameter("id");
        String userId = (String) req.getSession().getAttribute("userId");

        FavoriteDAO dao = new FavoriteImpl();
        dao.unlike(userId, restaurantId);

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
