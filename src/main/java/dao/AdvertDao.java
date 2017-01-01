package dao;

import model.Advert;

import java.util.List;

public interface AdvertDao {

    Advert getAdvertById(int id);

    List<Advert> getAllAdverts();

    List<Advert> getAdverts(boolean moderated);

    void addAdvert(Advert advert);

    void updateAdvert(Advert advert);

    void deleteAdvert(Advert advert);
}
