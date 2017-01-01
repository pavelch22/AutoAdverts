package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAutoCatalogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("authentication");
            return;
        }
        if (!user.getRole().getName().equals("admin")) {
            throw new ServletException("You don't have enough permissions");
        }
        req.getRequestDispatcher("WEB-INF/jsp/auto/catalog.jsp").forward(req, resp);
    }
}
