package controller;

import dao.mysql.ModelDaoImpl;
import model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteModelController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = new Model();
        model.setId(Integer.valueOf(req.getParameter("id")));
        new ModelDaoImpl().deleteModel(model);
    }
}
