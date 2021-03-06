package controller;

import dao.mysql.EngineTypeDaoImpl;
import model.EngineType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEngineTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EngineType engineType = new EngineType();
        engineType.setId(Integer.valueOf(req.getParameter("id")));
        new EngineTypeDaoImpl().deleteEngineType(engineType);
    }
}
