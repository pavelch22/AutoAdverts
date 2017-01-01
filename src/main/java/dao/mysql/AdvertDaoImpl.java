package dao.mysql;

import dao.AdvertDao;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.sql.*;
import java.util.*;

public class AdvertDaoImpl implements AdvertDao {
    private static final Logger LOGGER = LogManager.getLogger(AdvertDaoImpl.class);

    @Override
    public Advert getAdvertById(int id) {
        LOGGER.debug("Retrieving advert: id = {}", id);
        Advert advert = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM advert " +
                    "INNER JOIN user ON advert.user_id = user.id " +
                    "INNER JOIN role ON role.id = user.role_id " +
                    "INNER JOIN model ON advert.model_id = model.id " +
                    "INNER JOIN engine_type ON advert.engine_type_id = engine_type.id " +
                    "INNER JOIN brand ON brand.id = model.brand_id " +
                    "LEFT JOIN photo ON advert.id = photo.advert_id " +
                    "WHERE advert.id = ?");
            stmt.setInt(1, id);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (advert == null) {
                    advert = new Advert();

                    advert.setId(rs.getInt("advert.id"));

                    User user = new User();
                    user.setId(rs.getInt("user.id"));
                    Role role = new Role();
                    role.setId(rs.getInt("role.id"));
                    role.setName(rs.getString("role.role_name"));
                    user.setRole(role);
                    user.setEmail(rs.getString("user.email"));
                    user.setPhone(rs.getString("user.phone"));
                    user.setCity(rs.getString("user.city"));
//                    user.setPassword(rs.getString("user.password"));
                    advert.setUser(user);

                    advert.setPostDate(rs.getTimestamp("advert.post_date"));

                    Model model = new Model();
                    model.setId(rs.getInt("model.id"));
                    Brand brand = new Brand();
                    brand.setId(rs.getInt("brand.id"));
                    brand.setName(rs.getString("brand.brand_name"));
                    model.setBrand(brand);
                    model.setName(rs.getString("model.model_name"));
                    advert.setModel(model);

                    advert.setYear(rs.getDate("advert.year"));

                    EngineType engineType = new EngineType();
                    engineType.setId(rs.getInt("engine_type.id"));
                    engineType.setName(rs.getString("engine_type.type_name"));
                    advert.setEngineType(engineType);

                    advert.setEngineVolume(rs.getDouble("advert.engine_volume"));

                    advert.setMileage(rs.getInt("advert.mileage"));

                    advert.setColor(rs.getString("advert.color"));

                    advert.setPrice(rs.getDouble("advert.price"));

                    advert.setComment(rs.getString("advert.comment"));

                    advert.setModerated(rs.getBoolean("advert.is_moderated"));
                }
                if (rs.getInt("photo.id") != 0) {
                    Photo photo = new Photo();
                    photo.setId(rs.getInt("photo.id"));
                    photo.setData(rs.getBytes("photo.data"));
                    if (advert.getPhotos() == null) {
                        advert.setPhotos(new ArrayList<>());
                    }
                    advert.getPhotos().add(photo);
                }
            }
            if (advert != null) {
                LOGGER.debug("Advert: id = {} was successfully retrieved", id);
            } else {
                LOGGER.debug("Advert: id = {} was not found", id);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve advert. {}", DbUtil.getErrorInfo(e), e);
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
        return advert;
    }

    @Override
    public List<Advert> getAllAdverts() {
        LOGGER.debug("Retrieving all adverts");
        Map<Integer, Advert> adverts = new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM advert " +
                    "INNER JOIN user ON advert.user_id = user.id " +
                    "INNER JOIN role ON role.id = user.role_id " +
                    "INNER JOIN model ON advert.model_id = model.id " +
                    "INNER JOIN engine_type ON advert.engine_type_id = engine_type.id " +
                    "INNER JOIN brand ON brand.id = model.brand_id " +
                    "LEFT JOIN photo ON advert.id = photo.advert_id ");
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Advert advert = null;
                if (adverts.containsKey(rs.getInt("advert.id"))) {
                    advert = adverts.get(rs.getInt("advert.id"));
                } else {
                    advert = new Advert();

                    advert.setId(rs.getInt("advert.id"));

                    User user = new User();
                    user.setId(rs.getInt("user.id"));
                    Role role = new Role();
                    role.setId(rs.getInt("role.id"));
                    role.setName("role.role_name");
                    user.setRole(role);
                    user.setEmail(rs.getString("user.email"));
                    user.setPhone(rs.getString("user.phone"));
                    user.setCity(rs.getString("user.city"));
//                user.setPassword(rs.getString("user.password"));
                    advert.setUser(user);

                    advert.setPostDate(rs.getTimestamp("advert.post_date"));

                    Model model = new Model();
                    model.setId(rs.getInt("model.id"));
                    Brand brand = new Brand();
                    brand.setId(rs.getInt("brand.id"));
                    brand.setName(rs.getString("brand.brand_name"));
                    model.setBrand(brand);
                    model.setName(rs.getString("model.model_name"));
                    advert.setModel(model);

                    advert.setYear(rs.getDate("advert.year"));

                    EngineType engineType = new EngineType();
                    engineType.setId(rs.getInt("engine_type.id"));
                    engineType.setName(rs.getString("engine_type.type_name"));
                    advert.setEngineType(engineType);

                    advert.setEngineVolume(rs.getDouble("advert.engine_volume"));

                    advert.setMileage(rs.getInt("advert.mileage"));

                    advert.setColor(rs.getString("advert.color"));

                    advert.setPrice(rs.getDouble("advert.price"));

                    advert.setComment(rs.getString("advert.comment"));

                    advert.setModerated(rs.getBoolean("advert.is_moderated"));

                    adverts.put(rs.getInt("advert.id"), advert);
                }

                if (rs.getInt("photo.id") != 0) {
                    Photo photo = new Photo();
                    photo.setId(rs.getInt("photo.id"));
//                    photo.setAdvert(advert);
                    photo.setData(rs.getBytes("photo.data"));
                    if (advert.getPhotos() == null) {
                        advert.setPhotos(new ArrayList<>());
                    }
                    advert.getPhotos().add(photo);
                }
            }
            LOGGER.debug("{} adverts were successfully retrieved", adverts.size());
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve adverts. {}", DbUtil.getErrorInfo(e), e);
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
        return new ArrayList<>(adverts.values());
    }

    @Override
    public List<Advert> getAdverts(boolean moderated) {
        LOGGER.debug("Retrieving all {} adverts", moderated ? "moderated" : "not moderated");
        Map<Integer, Advert> adverts = new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM advert " +
                    "INNER JOIN user ON advert.user_id = user.id " +
                    "INNER JOIN role ON role.id = user.role_id " +
                    "INNER JOIN model ON advert.model_id = model.id " +
                    "INNER JOIN engine_type ON advert.engine_type_id = engine_type.id " +
                    "INNER JOIN brand ON brand.id = model.brand_id " +
                    "LEFT JOIN photo ON advert.id = photo.advert_id " +
                    "WHERE advert.is_moderated = ?");
            stmt.setBoolean(1, moderated);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Advert advert = null;
                if (adverts.containsKey(rs.getInt("advert.id"))) {
                    advert = adverts.get(rs.getInt("advert.id"));
                } else {
                    advert = new Advert();

                    advert.setId(rs.getInt("advert.id"));

                    User user = new User();
                    user.setId(rs.getInt("user.id"));
                    Role role = new Role();
                    role.setId(rs.getInt("role.id"));
                    role.setName("role.role_name");
                    user.setRole(role);
                    user.setEmail(rs.getString("user.email"));
                    user.setPhone(rs.getString("user.phone"));
                    user.setCity(rs.getString("user.city"));
//                user.setPassword(rs.getString("user.password"));
                    advert.setUser(user);

                    advert.setPostDate(rs.getTimestamp("advert.post_date"));

                    Model model = new Model();
                    model.setId(rs.getInt("model.id"));
                    Brand brand = new Brand();
                    brand.setId(rs.getInt("brand.id"));
                    brand.setName(rs.getString("brand.brand_name"));
                    model.setBrand(brand);
                    model.setName(rs.getString("model.model_name"));
                    advert.setModel(model);

                    advert.setYear(rs.getDate("advert.year"));

                    EngineType engineType = new EngineType();
                    engineType.setId(rs.getInt("engine_type.id"));
                    engineType.setName(rs.getString("engine_type.type_name"));
                    advert.setEngineType(engineType);

                    advert.setEngineVolume(rs.getDouble("advert.engine_volume"));

                    advert.setMileage(rs.getInt("advert.mileage"));

                    advert.setColor(rs.getString("advert.color"));

                    advert.setPrice(rs.getDouble("advert.price"));

                    advert.setComment(rs.getString("advert.comment"));

                    advert.setModerated(rs.getBoolean("advert.is_moderated"));

                    adverts.put(rs.getInt("advert.id"), advert);
                }

                if (rs.getInt("photo.id") != 0) {
                    Photo photo = new Photo();
                    photo.setId(rs.getInt("photo.id"));
//                    photo.setAdvert(advert);
                    photo.setData(rs.getBytes("photo.data"));
                    if (advert.getPhotos() == null) {
                        advert.setPhotos(new ArrayList<>());
                    }
                    advert.getPhotos().add(photo);
                }
            }
            LOGGER.debug("{} {} adverts were successfully retrieved", adverts.size(), moderated ? "moderated" : "not moderated");
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve adverts. {}", DbUtil.getErrorInfo(e), e);
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
        return new ArrayList<>(adverts.values());
    }

    @Override
    public void addAdvert(Advert advert) {
        LOGGER.debug("Adding advert");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO " +
                    "advert(user_id, post_date, model_id, year, engine_type_id, engine_volume, mileage, color, price, comment, is_moderated) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, advert.getUser().getId());
            stmt.setTimestamp(2, advert.getPostDate());
            stmt.setInt(3, advert.getModel().getId());
            stmt.setDate(4, advert.getYear(), Calendar.getInstance(TimeZone.getDefault()));
            stmt.setInt(5, advert.getEngineType().getId());
            stmt.setDouble(6, advert.getEngineVolume());
            stmt.setInt(7, advert.getMileage());
            stmt.setString(8, advert.getColor());
            stmt.setDouble(9, advert.getPrice());
            stmt.setString(10, advert.getComment());
            stmt.setBoolean(11, advert.isModerated());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                advert.setId(rs.getInt(1));
            }
            LOGGER.debug("Advert was successfully added. Generated id = {}", advert.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to add advert. {}", DbUtil.getErrorInfo(e), e);
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
    }

    @Override
    public void updateAdvert(Advert advert) {
        LOGGER.debug("Updating advert: id = {}", advert.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE advert SET is_moderated = ? WHERE id = ?");
            stmt.setBoolean(1, advert.isModerated());
            stmt.setInt(2, advert.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Advert: id = {} was successfully updated", advert.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to update advert. {}", DbUtil.getErrorInfo(e), e);
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
    public void deleteAdvert(Advert advert) {
        LOGGER.debug("Deleting advert: id = {}", advert.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM advert WHERE id = ?");
            stmt.setInt(1, advert.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Advert: id = {} was successfully deleted", advert.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to delete advert. {}", DbUtil.getErrorInfo(e), e);
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
