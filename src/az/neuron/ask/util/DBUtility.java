package az.neuron.ask.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {

    public static Connection getConnection() throws Exception {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/askdb");
        Connection connection = ds.getConnection();
        if (connection != null) {
            connection.setAutoCommit(false);
        } else {
            throw new Exception("getConnection() Connection is null ");
        }
        return connection;
    }

    public static Connection getConnectionDriverManager() throws Exception {
//        DriverManager.registerDriver(new OracleDriver());
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "19890925");
//        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.190.10:1521:orcl", "SYSTEM", "1q2w3e4r");
//        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.65.24:1521:ORCL", "SYSTEM", "test");
        return connection;
    }
}
