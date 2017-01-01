package dao.mysql;

import dao.UserDao;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User getUserById(int id) {
        LOGGER.debug("Retrieving user: id = {}", id);
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
            stmt.setInt(1, id);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user.id"));
                user.setEmail(rs.getString("user.email"));
                user.setPhone(rs.getString("user.phone"));
                user.setCity(rs.getString("user.city"));
                user.setPassword(rs.getString("user.password"));
            }
            if (user != null) {
                LOGGER.debug("User: id = {} was successfully retrieved", id);
            } else {
                LOGGER.debug("user: id = {} was not found", id);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve user. {}", DbUtil.getErrorInfo(e), e);
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
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        LOGGER.debug("Retrieving user: email = {}", email);
        User user = null;
        Map<Integer, Advert> adverts = new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM user INNER JOIN role ON user.role_id = role.id " +
                    "LEFT JOIN advert ON advert.user_id = user.id " +
                    "LEFT JOIN model ON model.id = advert.model_id " +
                    "LEFT JOIN brand ON brand.id = model.brand_id " +
                    "LEFT JOIN engine_type ON engine_type.id = advert.engine_type_id " +
                    "LEFT JOIN photo ON advert.id = photo.advert_id " +
                    "WHERE user.email LIKE ?");
//                    "WHERE user.email LIKE ? AND user.password COLLATE utf8_bin LIKE ?");
            stmt.setString(1, email);
//            stmt.setString(2, password);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (user == null) {
                    user = new User();

                    user.setId(rs.getInt("user.id"));

                    Role role = new Role();
                    role.setId(rs.getInt("role.id"));
                    role.setName(rs.getString("role.role_name"));
                    user.setRole(role);

                    user.setEmail(rs.getString("user.email"));

                    user.setPhone(rs.getString("user.phone"));

                    user.setCity(rs.getString("user.city"));

                    user.setPassword(rs.getString("user.password"));
                }

                Advert advert = null;
                if (adverts.containsKey(rs.getInt("advert.id"))) {
                    advert = adverts.get(rs.getInt("advert.id"));
                } else if (rs.getInt("advert.id") != 0) {
                    advert = new Advert();

                    advert.setId(rs.getInt("advert.id"));

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

                    adverts.put(advert.getId(), advert);
                }

                if (advert != null && rs.getInt("photo.id") != 0) {
                    Photo photo = new Photo();
                    photo.setId(rs.getInt("photo.id"));
                    photo.setData(rs.getBytes("photo.data"));
                    if (advert.getPhotos() == null) {
                        advert.setPhotos(new ArrayList<>());
                    }
                    advert.getPhotos().add(photo);
                }
            }

            if (user != null) {
                user.setAdverts(new ArrayList<>(adverts.values()));
                LOGGER.debug("User: email = {} was successfully retrieved", email);
            } else {
                LOGGER.debug("User: email = {} was not found", email);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve user. {}", DbUtil.getErrorInfo(e), e);
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
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("Retrieving all users");
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM user INNER JOIN role ON user.role_id = role.id");
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("user.id"));

                Role role = new Role();
                role.setId(rs.getInt("role.id"));
                role.setName(rs.getString("role.role_name"));
                user.setRole(role);

                user.setEmail(rs.getString("user.email"));

                user.setPhone(rs.getString("user.phone"));

                user.setCity(rs.getString("user.city"));

                user.setPassword(rs.getString("user.password"));

                users.add(user);
            }
            LOGGER.debug("{} users were successfully retrieved", users.size());
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve users. {}", DbUtil.getErrorInfo(e), e);
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
        return users;
    }

    @Override
    public void addUser(User user) {
        LOGGER.debug("Adding user: email = {}", user.getEmail());
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO user(role_id, email, phone, city, password) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, user.getRole().getId());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getCity());
            stmt.setString(5, user.getPassword());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            LOGGER.debug("User was successfully added. Generated id = {}", user.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to add user. {}", DbUtil.getErrorInfo(e), e);
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
    public void updateUser(User user) {
        LOGGER.debug("Updating user: id = {}", user.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE user SET role_id = ?, email = ?, phone = ?, city = ?, password = ? WHERE id = ?");
            stmt.setInt(1, user.getRole().getId());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getCity());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("User was successfully updated");
        } catch (SQLException e) {
            LOGGER.error("Unable to update user. {}", DbUtil.getErrorInfo(e), e);
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
    public void deleteUser(User user) {
        LOGGER.debug("Deleting user: id = {}", user.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM user WHERE id = ?");
            stmt.setInt(1, user.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("User was successfully deleted");
        } catch (SQLException e) {
            LOGGER.error("Unable to delete user. {}", DbUtil.getErrorInfo(e), e);
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
