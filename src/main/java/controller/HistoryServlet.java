package controller;

import dao.ViewHistoryDAO;
import dao.ViewHistoryImpl;
import entity.ViewHistory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ⚠️ GIẢ LẬP LOGIN (sau này thay bằng session)
        String userId = "u1";

        ViewHistoryDAO dao = new ViewHistoryImpl();
        List<ViewHistory> list = dao.findByUser(userId);

        // 🔥 FORMAT LocalDateTime → String để JSP hiển thị
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        list.forEach(h -> {
            if (h.getViewedAt() != null) {
                h.setViewedAtFormatted(
                        h.getViewedAt().format(formatter)
                );
            }
        });

        req.setAttribute("list", list);

        // gắn content
        req.setAttribute("contentPage", "/views/history.jsp");

        // đi qua layout
        req.getRequestDispatcher("/views/layout.jsp")
                .forward(req, resp);
    }
}
