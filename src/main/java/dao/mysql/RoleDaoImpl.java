package dao.mysql;

import dao.RoleDao;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);

    @Override
    public Role getRole(String roleName) {
        LOGGER.debug("Retrieving role: name = {}", roleName);
        Role role = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM role WHERE role_name = ?");
            stmt.setString(1, roleName);
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            if (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("role_name"));
            }
            if (role != null) {
                LOGGER.debug("Role: name = {} was successfully retrieved", roleName);
            } else {
                LOGGER.debug("Role: name = {} was not found", roleName);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve role. {}", DbUtil.getErrorInfo(e), e);
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
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        LOGGER.debug("Retrieving all roles");
        List<Role> roles = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM role");
            LOGGER.debug("Statement: {}", stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("role_name"));
                roles.add(role);
            }
            LOGGER.debug("{} roles were successfully retrieved", roles.size());
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve roles. {}", DbUtil.getErrorInfo(e), e);
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
        return roles;
    }
}
