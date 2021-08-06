package com.t2012e.assignment.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionHelper {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/%s";
    private static final String DATABASE_NAME = "human_resource";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PWD = "";
    private static Connection cnn;

    public static Connection getConnection() throws SQLException {
        if (cnn == null || cnn.isClosed()) {
            cnn = DriverManager.getConnection(
                    String.format(DATABASE_URL, DATABASE_NAME),
                    DATABASE_USER,
                    DATABASE_PWD);
        }

        return cnn;
    }
}
