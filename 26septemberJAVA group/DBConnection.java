//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
//IRADUKUNDA Epiphanie 223015618package com.SFMS;
import java.sql.*;

public class DBConnection {
    // Update these values with your actual MySQL credentials
    private static final String URL = "jdbc:mysql://localhost:3306/september26Group28JAVA";
    private static final String USERNAME = "root";  // Change if you use different username
    private static final String PASSWORD = "";      // Change to your MySQL password
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

}
