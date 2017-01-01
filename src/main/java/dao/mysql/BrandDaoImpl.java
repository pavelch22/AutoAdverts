package dao.mysql;

import dao.BrandDao;
import model.Brand;
import model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrandDaoImpl implements BrandDao {
    private static final Logger LOGGER = LogManager.getLogger(BrandDaoImpl.class);

    @Override
    public Brand getBrandById(int id) {
        LOGGER.debug("Retrieving brand: id = {}", id);
        Brand brand = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM brand LEFT JOIN model ON brand.id = model.brand_id WHERE brand.id = ?");
            stmt.setInt(1, id);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (brand == null) {
                    brand = new Brand();
                    brand.setId(rs.getInt("brand.id"));
                    brand.setName(rs.getString("brand.brand_name"));
                    brand.setModels(new ArrayList<>());
                }
                if (rs.getInt("model.id") != 0) {
                    Model model = new Model();
                    model.setId(rs.getInt("model.id"));
                    model.setBrand(brand);
                    model.setName(rs.getString("model.model_name"));
                    brand.getModels().add(model);
                }
            }
            if (brand != null) {
                LOGGER.debug("Brand: id = {} was successfully retrieved", id);
            } else {
                LOGGER.debug("Brand: id = {} was not found", id);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve brand. {}", DbUtil.getErrorInfo(e), e);
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
        return brand;
    }

    @Override
    public List<Brand> getAllBrands() {
        LOGGER.debug("Retrieving all brands");
        Map<Integer, Brand> brands = new HashMap<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM brand LEFT JOIN model ON brand.id = model.brand_id");
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("brand.id");
                if (brands.containsKey(id)) {
                    Brand brand = brands.get(id);
                    Model model = new Model();
                    model.setId(rs.getInt("model.id"));
                    model.setBrand(brand);
                    model.setName(rs.getString("model.model_name"));
                    brand.getModels().add(model);
                } else {
                    Brand brand = new Brand();
                    brand.setId(id);
                    brand.setName(rs.getString("brand.brand_name"));
                    brand.setModels(new ArrayList<>());
                    Model model = new Model();
                    model.setId(rs.getInt("model.id"));
                    model.setBrand(brand);
                    model.setName(rs.getString("model.model_name"));
                    brand.getModels().add(model);
                    brands.put(id, brand);
                }
            }
            LOGGER.debug("{} brands were successfully retrieved", brands.size());
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve brands. {}", DbUtil.getErrorInfo(e), e);
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
        return new ArrayList<>(brands.values());
    }

    @Override
    public void addBrand(Brand brand) {
        LOGGER.debug("Adding brand: name = {}", brand.getName());
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO brand(brand_name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, brand.getName());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                brand.setId(rs.getInt(1));
            }
            LOGGER.debug("Brand was successfully added. Generated id = {}", brand.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to add brand. {}", DbUtil.getErrorInfo(e), e);
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
    public void updateBrand(Brand brand) {
        LOGGER.debug("Updating brand: id = {}, name = {}", brand.getId(), brand.getName());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE brand SET brand_name = ? WHERE id = ?");
            stmt.setString(1, brand.getName());
            stmt.setInt(2, brand.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Brand: id = {} was successfully updated", brand.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to update brand. {}", DbUtil.getErrorInfo(e), e);
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
    public void deleteBrand(Brand brand) {
        LOGGER.debug("Deleting brand: id = {}", brand.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM brand WHERE id = ?");
            stmt.setInt(1, brand.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Brand: id = {} was successfully deleted", brand.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to delete brand. {}", DbUtil.getErrorInfo(e), e);
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
