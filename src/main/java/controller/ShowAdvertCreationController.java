package controller;

import dao.mysql.EngineTypeDaoImpl;
import dao.mysql.ModelDaoImpl;
import dao.mysql.UserDaoImpl;
import model.EngineType;
import model.Model;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShowAdvertCreationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("authentication");
            return;
        }

        List<Model> models = new ModelDaoImpl().getAllModels();
        List<EngineType> engineTypes = new EngineTypeDaoImpl().getAllEngineTypes();
        req.setAttribute("models", models);
        req.setAttribute("engineTypes", engineTypes);

        req.getRequestDispatcher("WEB-INF/jsp/advert/create.jsp").forward(req, resp);
    }
}
