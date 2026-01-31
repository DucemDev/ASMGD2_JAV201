package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String restaurantId = req.getParameter("id");
        String userId = (String) req.getSession().getAttribute("userId");

        // test tạm
        System.out.println("LIKE SERVLET HIT: " + restaurantId);

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
