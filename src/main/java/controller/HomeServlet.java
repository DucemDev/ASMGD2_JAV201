package controller;

import dao.RestaurantDAO;
import dao.RestaurantImpl;
import entity.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet({"/", "/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RestaurantDAO dao = new RestaurantImpl();

        String keyword = req.getParameter("keyword");
        List<Restaurant> list;

        if (keyword != null && !keyword.trim().isEmpty()) {
            list = dao.searchByName(keyword);
        } else {
            list = dao.findall();
        }

        System.out.println("HOME LIST SIZE = " + list.size());

        req.setAttribute("list", list);

        // layout + content
        req.setAttribute("contentPage", "home-content.jsp");

        req.getRequestDispatcher("/views/layout.jsp")
                .forward(req, resp);
    }
}
