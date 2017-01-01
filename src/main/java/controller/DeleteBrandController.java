package controller;

import dao.mysql.BrandDaoImpl;
import model.Brand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBrandController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Brand brand = new Brand();
        brand.setId(Integer.parseInt(req.getParameter("id")));
        new BrandDaoImpl().deleteBrand(brand);
    }
}
