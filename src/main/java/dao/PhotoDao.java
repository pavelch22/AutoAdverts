package dao;

import model.Photo;

import java.util.List;

public interface PhotoDao {

    Photo getPhotoById(int id);

    void addPhoto(Photo photo);

    void addPhotos(List<Photo> photos);

    void deletePhoto(Photo photo);
}
