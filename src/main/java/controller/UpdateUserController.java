package controller;

import dao.mysql.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String phone = req.getParameter("phone");
        String city = req.getParameter("city");
        String password = req.getParameter("pass");
        if (phone != null) {
            user.setPhone(phone);
        }
        if (city != null) {
            user.setCity(city);
        }
        if (password != null) {
            user.setPassword(password);
        }
        new UserDaoImpl().updateUser(user);
        resp.sendRedirect("profile");
    }
}
