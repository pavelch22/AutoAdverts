package controller;

import dao.UserDao;
import dao.mysql.RoleDaoImpl;
import dao.mysql.UserDaoImpl;
import model.Role;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RevokeModeratorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        UserDao userDao = new UserDaoImpl();
        User current = userDao.getUserById(id);
        Role user = new RoleDaoImpl().getRole("user");
        current.setRole(user);
        userDao.updateUser(current);
    }
}
