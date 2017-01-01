package dao.mysql;

import dao.ModelDao;
import model.Brand;
import model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelDaoImpl implements ModelDao {
    private static final Logger LOGGER = LogManager.getLogger(ModelDaoImpl.class);

    @Override
    public Model getModelById(int id) {
        LOGGER.debug("Retrieving model: id = {}", id);
        Model model = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM model INNER JOIN brand ON model.brand_id = brand.id WHERE model.id = ?");
            stmt.setInt(1, id);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("brand.id"));
                brand.setName(rs.getString("brand.brand_name"));
                model = new Model();
                model.setId(rs.getInt("model.id"));
                model.setBrand(brand);
                model.setName(rs.getString("model.model_name"));
            }
            if (model != null) {
                LOGGER.debug("Model: id = {} was successfully retrieved", id);
            } else {
                LOGGER.debug("Model: id = {} was not found", id);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve model. {}", DbUtil.getErrorInfo(e), e);
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
        return model;
    }

    @Override
    public List<Model> getAllModels() {
        LOGGER.debug("Retrieving all models");
        List<Model> models = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM model INNER JOIN brand ON model.brand_id = brand.id");
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("brand.id"));
                brand.setName(rs.getString("brand.brand_name"));
                Model model = new Model();
                model.setId(rs.getInt("model.id"));
                model.setBrand(brand);
                model.setName(rs.getString("model.model_name"));
                models.add(model);
            }
            LOGGER.debug("{} models were successfully retrieved", models.size());
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve models. {}", DbUtil.getErrorInfo(e), e);
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
        return models;
    }

    @Override
    public void addModel(Model model) {
        LOGGER.debug("Adding model: name = {}", model.getName());
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO model(brand_id, model_name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, model.getBrand().getId());
            stmt.setString(2, model.getName());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                model.setId(rs.getInt(1));
            }
            LOGGER.debug("Model was successfully added. Generated id = {}", model.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to add model. {}", DbUtil.getErrorInfo(e), e);
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
    public void updateModel(Model model) {
        LOGGER.debug("Updating model: id = {}", model.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE model SET model_name = ? WHERE id = ?");
            stmt.setString(1, model.getName());
            stmt.setInt(2, model.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Model was successfully updated");
        } catch (SQLException e) {
            LOGGER.error("Unable to update model. {}", DbUtil.getErrorInfo(e), e);
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
    public void deleteModel(Model model) {
        LOGGER.debug("Deleting model: id = {}", model.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM model WHERE id = ?");
            stmt.setInt(1, model.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Model was successfully deleted");
        } catch (SQLException e) {
            LOGGER.error("Unable to delete model. {}", DbUtil.getErrorInfo(e), e);
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
