package controller;

import com.google.gson.Gson;
import dao.mysql.EngineTypeDaoImpl;
import model.EngineType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowEngineTypesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EngineType> engineTypes = new EngineTypeDaoImpl().getAllEngineTypes();
        String json = new Gson().toJson(engineTypes);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
