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
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

public class UserDAO {
    
    // Get user by ID
    public static User getUserById(int userId) {
        User user = null;
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn != null) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("gender")
                    );
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error loading user: " + ex.getMessage());
            }
        }
        return user;
    }
    
    // Update user profile
    public static boolean updateUser(User user) {
        Connection conn = DatabaseConnection.getConnection();
        boolean success = false;
        
        if (conn != null) {
            String sql = "UPDATE users SET username = ?, email = ?, age = ?, gender = ? WHERE user_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getEmail());
                pstmt.setInt(3, user.getAge());
                pstmt.setString(4, user.getGender());
                pstmt.setInt(5, user.getUserID());
                
                int rowsAffected = pstmt.executeUpdate();
                success = rowsAffected > 0;
                
                if (success) {
                    System.out.println("User updated successfully: " + user.getUsername());
                } else {
                    System.out.println("No user found with ID: " + user.getUserID());
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error updating profile: " + ex.getMessage());
                success = false;
            }
        }
        return success;
    }
    
    // Update password
    public static boolean updatePassword(int userId, String currentPassword, String newPassword) {
        Connection conn = DatabaseConnection.getConnection();
        boolean success = false;
        
        if (conn != null) {
            String verifySql = "SELECT password FROM users WHERE user_id = ?";
            try (PreparedStatement verifyStmt = conn.prepareStatement(verifySql)) {
                verifyStmt.setInt(1, userId);
                ResultSet rs = verifyStmt.executeQuery();
                
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    String currentPasswordHash = hashPassword(currentPassword);
                    
                    if (storedHash.equals(currentPasswordHash)) {
                        String updateSql = "UPDATE users SET password = ? WHERE user_id = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                            String newPasswordHash = hashPassword(newPassword);
                            updateStmt.setString(1, newPasswordHash);
                            updateStmt.setInt(2, userId);
                            
                            int rowsAffected = updateStmt.executeUpdate();
                            success = rowsAffected > 0;
                            
                            if (success) {
                                System.out.println("Password updated successfully for user ID: " + userId);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Current password is incorrect!");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error updating password: " + ex.getMessage());
            }
        }
        return success;
    }
    // Check
    public static boolean isUsernameAvailable(String username, int currentUserId) {
        Connection conn = DatabaseConnection.getConnection();
        boolean available = true;
        
        if (conn != null) {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND user_id != ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setInt(2, currentUserId);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    int count = rs.getInt(1);
                    available = count == 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                available = false;
            }
        }
        return available;
    }
    
    // Check
    public static boolean isEmailAvailable(String email, int currentUserId) {
        Connection conn = DatabaseConnection.getConnection();
        boolean available = true;
        
        if (conn != null) {
            String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND user_id != ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, email);
                pstmt.setInt(2, currentUserId);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    int count = rs.getInt(1);
                    available = count == 0;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                available = false;
            }
        }
        return available;
    }
    
    // Helper method to hash password (simplified - implement proper hashing in production)
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            
            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error hashing password", e);
            return password; // Fallback to plain text (not secure!)
        }
    }
    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }
}
