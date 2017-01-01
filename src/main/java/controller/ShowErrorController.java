package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowErrorController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ShowErrorController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.error("Exception: {}", req.getAttribute("javax.servlet.error.exception"));
        req.setAttribute("message", req.getAttribute("javax.servlet.error.message"));
        req.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.error("Exception: {}", req.getAttribute("javax.servlet.error.exception"));
        req.setAttribute("message", req.getAttribute("javax.servlet.error.message"));
        req.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(req, resp);
    }
}
