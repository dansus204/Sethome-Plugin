package dansus204.database;

import java.sql.*;

public class DatabaseConnector {

    private final Connection connection;

    public DatabaseConnector(String address) {

        try {
            connection = DriverManager.getConnection(address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

}
