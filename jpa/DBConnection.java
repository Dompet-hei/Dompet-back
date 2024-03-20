package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static private Connection connection;
    static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/test_jpa",
                        "postgres",
                        "axel"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    };
}
