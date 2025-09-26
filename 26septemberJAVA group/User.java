package com.SFMS;

//User.java
import java.time.LocalDateTime;

public class User {
 private int userID;
 private String username;
 private String passwordHash;
 private String role;
 private String email;
 private LocalDateTime createdAt;
 
 // Constructors
 public User() {}
 
 public User(int userID, String username, String passwordHash, String role, String email, LocalDateTime createdAt) {
     this.userID = userID;
     this.username = username;
     this.passwordHash = passwordHash;
     this.role = role;
     this.email = email;
     this.createdAt = createdAt;
 }
 
 // Getters and Setters
 public int getUserID() { return userID; }
 public void setUserID(int userID) { this.userID = userID; }
 
 public String getUsername() { return username; }
 public void setUsername(String username) { this.username = username; }
 
 public String getPasswordHash() { return passwordHash; }
 public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
 
 public String getRole() { return role; }
 public void setRole(String role) { this.role = role; }
 
 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }
 
 public LocalDateTime getCreatedAt() { return createdAt; }
 public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}