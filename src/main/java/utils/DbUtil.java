package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    private static final Logger LOGGER = LogManager.getLogger(DbUtil.class);

    public static Connection getConnection() {
        Connection conn = null;
        try {
            LOGGER.debug("Trying to obtain connection");
            Context initialContext = new InitialContext();
            DataSource ds = (DataSource) initialContext.lookup("java:comp/env/jdbc/adverts");
            conn = ds.getConnection();
            LOGGER.debug("Connection was successfully obtained");
        } catch (NamingException | SQLException e) {
            LOGGER.error("Unable to obtain connection", e);
        }
        return conn;
    }

    public static String getErrorInfo(SQLException e) {
        return "SQLState: " + e.getSQLState() + ", ErrorCode: " + e.getErrorCode();
    }
}
