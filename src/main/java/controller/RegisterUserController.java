package controller;

import dao.mysql.RoleDaoImpl;
import dao.mysql.UserDaoImpl;
import model.Role;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoImpl userDao = new UserDaoImpl();

        if (userDao.getUserByEmail(req.getParameter("email")) != null) {
            throw new ServletException("A user with this email already exists");
        }

        Role role = new RoleDaoImpl().getRole("user");
        User user = new User();
        user.setRole(role);
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));
        user.setCity(req.getParameter("city"));
        user.setPassword(req.getParameter("password"));
        userDao.addUser(user);
        resp.sendRedirect("authentication");
    }
}