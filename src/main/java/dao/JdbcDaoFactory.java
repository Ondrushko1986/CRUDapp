package dao;

import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mytestdb?serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(DB_DRIVER).newInstance());
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public UserDao getUserDao () {
        return new UserDaoJDBC(getConnection());
    }

}
