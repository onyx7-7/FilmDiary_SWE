/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package software_project;

/**
 *
 * @author norah
 */

public class User {

    private int userID;
    private String username;
    private String password; // Should store the HASHED password
    private String email;
    private int age;
    private String gender; // e.g., "Male", "Female"

    // Constructor 1: For creating a new user (without userID)
    public User(String username, String password, String email, int age, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    // Constructor 2: For retrieving an existing user from the database
    public User(int userID, String username, String password, String email, int age, String gender) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    // Getters
    public int getUserID() { return userID; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    // Setters
    public void setUserID(int userID) { this.userID = userID; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
}
