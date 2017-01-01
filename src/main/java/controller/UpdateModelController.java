package controller;

import dao.mysql.ModelDaoImpl;
import model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateModelController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = new Model();
        model.setId(Integer.parseInt(req.getParameter("id")));
        model.setName(req.getParameter("modelName"));
        new ModelDaoImpl().updateModel(model);
    }
}
