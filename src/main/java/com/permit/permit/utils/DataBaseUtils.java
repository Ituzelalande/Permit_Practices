package com.permit.permit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        String urls = "jdbc:mysql://localhost:3307/perimi";
        String userName = "root";
        String passcode = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(urls, userName, passcode);

        return connection;

    }
}
