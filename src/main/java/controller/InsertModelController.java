package controller;

import dao.mysql.ModelDaoImpl;
import model.Brand;
import model.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertModelController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = new Model();
        Brand brand = new Brand();
        brand.setId(Integer.parseInt(req.getParameter("brandId")));
        model.setBrand(brand);
        model.setName(req.getParameter("modelName"));
        new ModelDaoImpl().addModel(model);
    }
}
