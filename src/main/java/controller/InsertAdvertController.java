package controller;

import dao.mysql.AdvertDaoImpl;
import dao.mysql.PhotoDaoImpl;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@MultipartConfig
public class InsertAdvertController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advert advert = new Advert();

        User user = (User) req.getSession().getAttribute("user");
        advert.setUser(user);

        advert.setPostDate(new Timestamp(System.currentTimeMillis()));

        int modelId = Integer.parseInt(req.getParameter("model"));
        Model model = new Model();
        model.setId(modelId);
        advert.setModel(model);


        java.util.Date year = null;
        try {
            year = new SimpleDateFormat("yyyy").parse(req.getParameter("year"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(year.getTime());

        advert.setYear(sqlDate);

        int engineTypeId = Integer.parseInt(req.getParameter("engineType"));
        EngineType engineType = new EngineType();
        engineType.setId(engineTypeId);
        advert.setEngineType(engineType);

        double volume = Double.parseDouble(req.getParameter("volume"));
        advert.setEngineVolume(volume);

        int mileage = Integer.parseInt(req.getParameter("mileage"));
        advert.setMileage(mileage);

        String color = req.getParameter("color");
        advert.setColor(color);

        double price = Double.parseDouble(req.getParameter("price"));
        advert.setPrice(price);

        String comment = req.getParameter("comment");
        advert.setComment(comment);

        advert.setModerated(false);

        new AdvertDaoImpl().addAdvert(advert);

        List<Photo> photos = new ArrayList<>();
        List<Part> parts = req.getParts().stream().filter(part -> "photo".equals(part.getName())).collect(Collectors.toList());
        for (Part part : parts) {
            InputStream inputStream = part.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            if (bytes.length > 0) {
                Photo photo = new Photo();
                photo.setAdvert(advert);
                photo.setData(bytes);
                photos.add(photo);
            }
        }

        if (photos.size() > 0) {
            new PhotoDaoImpl().addPhotos(photos);
        }

        resp.sendRedirect("show_adverts");
    }
}
