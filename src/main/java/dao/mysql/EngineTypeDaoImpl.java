package dao.mysql;

import dao.EngineTypeDao;
import model.EngineType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineTypeDaoImpl implements EngineTypeDao {
    private static final Logger LOGGER = LogManager.getLogger(EngineTypeDaoImpl.class);

    @Override
    public EngineType getEngineTypeById(int id) {
        LOGGER.debug("Retrieving engine type: id = {}", id);
        EngineType engineType = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM engine_type WHERE id = ?");
            stmt.setInt(1, id);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            if (rs.next()) {
                engineType = new EngineType();
                engineType.setId(rs.getInt("id"));
                engineType.setName(rs.getString("type_name"));
            }
            if (engineType != null) {
                LOGGER.debug("Engine type: id = {} was successfully retrieved", id);
            } else {
                LOGGER.debug("Engine type: id = {} was not found", id);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve engine type. {}", DbUtil.getErrorInfo(e), e);
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
        return engineType;
    }

    @Override
    public List<EngineType> getAllEngineTypes() {
        LOGGER.debug("Retrieving all engine types");
        List<EngineType> engineTypes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM engine_type");
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                EngineType engineType = new EngineType();
                engineType.setId(rs.getInt("id"));
                engineType.setName(rs.getString("type_name"));
                engineTypes.add(engineType);
            }
            LOGGER.debug("{} engine types were retrieved", engineTypes.size());
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve engine types. {}", DbUtil.getErrorInfo(e), e);
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
        return engineTypes;
    }

    @Override
    public void addEngineType(EngineType engineType) {
        LOGGER.debug("Adding engine type: name = ", engineType.getName());
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("INSERT INTO engine_type(type_name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, engineType.getName());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                engineType.setId(rs.getInt(1));
            }
            LOGGER.debug("Engine type was successfully added. Generated id = {}", engineType.getId());
        } catch (SQLException e) {
            LOGGER.error("Unable to add engine type. {}", DbUtil.getErrorInfo(e), e);
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
    public void updateEngineType(EngineType engineType) {
        LOGGER.debug("Updating engine type: id = {}", engineType.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE engine_type SET type_name = ? WHERE id = ?");
            stmt.setString(1, engineType.getName());
            stmt.setInt(2, engineType.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug(("Engine type was successfully updated"));
        } catch (SQLException e) {
            LOGGER.error("Unable to update engine type. {}", DbUtil.getErrorInfo(e), e);
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
    public void deleteEngineType(EngineType engineType) {
        LOGGER.debug("Deleting engine type: id = {}", engineType.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM engine_type WHERE id = ?");
            stmt.setInt(1, engineType.getId());
            LOGGER.debug("Statement: {}", stmt);
            stmt.executeUpdate();
            LOGGER.debug("Engine type was successfully deleted");
        } catch (SQLException e) {
            LOGGER.error("Unable to delete engine type. {}", DbUtil.getErrorInfo(e), e);
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
