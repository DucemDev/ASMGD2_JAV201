package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {"/like", "/unlike", "/favorite", "/share"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            // CHƯA LOGIN → QUAY LẠI TRANG TRƯỚC
            String referer = req.getHeader("Referer");
            resp.sendRedirect(
                    referer != null ? referer : req.getContextPath() + "/home"
            );
            return;
        }

        chain.doFilter(request, response);
    }
}

