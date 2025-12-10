/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package software_project;

/**
 *
 * @author norah
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/movie?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root"; // 
    private static final String PASSWORD = "ww08642A"; //
    private static Connection connection = null;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    System.out.println("Database connected successfully!");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"Database connection failed!\n" + "Error: " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    /*public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
                connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    */
    
    // Test connection
    /*public static boolean testConnection() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    */
}
