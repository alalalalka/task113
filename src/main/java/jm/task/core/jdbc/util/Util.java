package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Util {
    private static String dbUrl = "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&useSSL=false";
    private static String dbUserName = "root";
    private static String dbPassword = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
