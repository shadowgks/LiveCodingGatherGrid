package com.epshiro.authentication;

import com.epshiro.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login.php")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> optionalUser = authService.login(req.getParameter("email"), req.getParameter("password"));

        if(optionalUser.isPresent()) {
            req.getSession(true).setAttribute("user", optionalUser.get());
            req.setAttribute("success", "You are logged in successfully");
            resp.sendRedirect(req.getContextPath()+"/home?success=" + req.getAttribute("success"));
        } else {
            req.setAttribute("error", "Invalid credentials");
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/login.jsp")
                    .forward(req, resp);
        }
    }
}
