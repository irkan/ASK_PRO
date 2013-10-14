package az.neuron.ask.util;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: Irkan Ahmadov
 * Date: 3/22/12
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class JdbcUtility {
    public static void close(ResultSet rs, PreparedStatement ps, Connection c) throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (ps != null) {
            ps.close();
        }

        if (c != null) {
            c.close();
        }
    }

    public static void close(ResultSet rs, CallableStatement cs, Connection c) throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (cs != null) {
            cs.close();
        }

        if (c != null) {
            c.close();
        }
    }

    public static void close(CallableStatement callableStatement, Connection connection) throws SQLException {
        if (callableStatement != null) {
            callableStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

}
