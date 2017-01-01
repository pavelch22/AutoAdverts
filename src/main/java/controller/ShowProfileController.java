package controller;

import dao.mysql.AdvertDaoImpl;
import dao.mysql.UserDaoImpl;
import model.Advert;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("authentication");
            return;
        }

        if ("moderator".equals(user.getRole().getName()) || "admin".equals(user.getRole().getName())) {
            //show moderation
            List<Advert> notModeratedAdverts = new AdvertDaoImpl().getAdverts(false);
            req.setAttribute("notModeratedAdverts", notModeratedAdverts);
        }
//        if (user.getRole().getName().equals("admin")) {
//            //show users
//            List<User> users = new UserDaoImpl().getAllUsers();
//            req.setAttribute("users", users);
//        }
        req.getSession().setAttribute("user", new UserDaoImpl().getUserByEmail(user.getEmail()));
        req.getRequestDispatcher("WEB-INF/jsp/auth/profile.jsp").forward(req, resp);
    }
}
