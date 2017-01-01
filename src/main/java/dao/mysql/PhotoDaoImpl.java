package dao.mysql;

import dao.PhotoDao;
import model.Photo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PhotoDaoImpl implements PhotoDao {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDaoImpl.class);

    @Override
    public Photo getPhotoById(int id) {
        LOGGER.debug("Retrieving photo: id = {}", id);
        Photo photo = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM photo WHERE id = ?");
            stmt.setInt(1, id);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            if (rs.next()) {
                photo = new Photo();
                photo.setId(rs.getInt("id"));
                photo.setData(rs.getBytes("data"));
            }
            if (photo != null) {
                LOGGER.debug("Photo: id = {} was successfully retrieved", id);
            } else {
                LOGGER.debug("Photo: id = {} was not found", id);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve photo. {}", DbUtil.getErrorInfo(e), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return photo;
    }

    @Override
    public void addPhoto(Photo photo) {
        LOGGER.debug("Adding photo");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO photo(advert_id, data) VALUES (?, ?)");
            stmt.setInt(1, photo.getAdvert().getId());
            stmt.setBlob(2, new ByteArrayInputStream(photo.getData()));
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Photo was successfully added");
        } catch (SQLException e) {
            LOGGER.error("Unable to add photo. {}", DbUtil.getErrorInfo(e), e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void addPhotos(List<Photo> photos) {
        LOGGER.debug("Adding photos");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("INSERT INTO photo(advert_id, data) VALUES (?, ?)");
            for (Photo photo : photos) {
                stmt.setInt(1, photo.getAdvert().getId());
                stmt.setBinaryStream(2, new ByteArrayInputStream(photo.getData()));
                stmt.addBatch();
            }
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeBatch();
            conn.commit();
            LOGGER.debug("Photos were successfully added");
        } catch (SQLException e) {
            LOGGER.error("Unable to add photos. {}", DbUtil.getErrorInfo(e), e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deletePhoto(Photo photo) {
        LOGGER.debug("Deleting photo: id = {}", photo.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM photo WHERE id = ?");
            stmt.setInt(1, photo.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Photo was successfully deleted");
        } catch (SQLException e) {
            LOGGER.error("Unable to delete photo. {}", DbUtil.getErrorInfo(e), e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
