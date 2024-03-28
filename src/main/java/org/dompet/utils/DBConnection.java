package org.dompet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    public static Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/dompet",
                        "postgres",
                        "yourPassword"
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    };
}
