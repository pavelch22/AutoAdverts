package controller;

import dao.mysql.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new UserDaoImpl().getUserByEmail(email);

        if (user != null && password.equals(user.getPassword())) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("show_adverts");
        } else {
            throw new ServletException("You have entered incorrect login or password.");
        }
    }
}
