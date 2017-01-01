package controller;

import dao.mysql.AdvertDaoImpl;
import model.Advert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAdvertsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Advert> adverts = new AdvertDaoImpl().getAdverts(true);
        req.setAttribute("moderatedAdverts", adverts);
        req.getRequestDispatcher("WEB-INF/jsp/advert/adverts.jsp").forward(req, resp);
    }
}
