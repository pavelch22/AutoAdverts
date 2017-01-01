package controller;

import dao.mysql.AdvertDaoImpl;
import model.Advert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAdvertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advert advert = new Advert();
        String id = req.getParameter("id");
        advert.setId(Integer.parseInt(id));
        new AdvertDaoImpl().deleteAdvert(advert);
        resp.sendRedirect("profile");
    }
}
