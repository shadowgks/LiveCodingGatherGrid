package com.epshiro.authentication;

import com.epshiro.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp")
            .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = new User(
                req.getParameter("lastname"),
                req.getParameter("firstname"),
                req.getParameter("email"),
                req.getParameter("password"));
        try {
            authService.registerUser(user);
            req.setAttribute("success", "Your account has been created successfully");
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getLocalizedMessage());
            getServletContext()
                .getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/login.php");
    }
}
