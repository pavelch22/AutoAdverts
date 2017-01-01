package controller;

import dao.mysql.AdvertDaoImpl;
import model.Advert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowAdvertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Advert advert = new AdvertDaoImpl().getAdvertById(id);
        req.setAttribute("advert", advert);
        req.getRequestDispatcher("WEB-INF/jsp/advert/details.jsp").forward(req, resp);
    }
}
