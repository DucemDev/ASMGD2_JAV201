package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ❗ CHƯA XỬ LÝ LOGIN nhá đức anh
        // ❗ CHỈ HIỂN THỊ GIAO DIỆN

        req.getRequestDispatcher("/views/login.jsp")
                .forward(req, resp);
    }
}
