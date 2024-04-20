package com.first.bms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bankSystemNew";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Mysqlpass";
    
    // Method to get database connection
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the database connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException (if MySQL JDBC driver is not found)
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            // Handle SQLException (connection failure)
            e.printStackTrace();
            throw new SQLException("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}
