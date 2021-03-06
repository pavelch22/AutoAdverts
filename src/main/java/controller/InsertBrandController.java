package controller;

import dao.mysql.BrandDaoImpl;
import model.Brand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertBrandController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Brand brand = new Brand();
        brand.setName(req.getParameter("brandName"));
        new BrandDaoImpl().addBrand(brand);
    }
}
