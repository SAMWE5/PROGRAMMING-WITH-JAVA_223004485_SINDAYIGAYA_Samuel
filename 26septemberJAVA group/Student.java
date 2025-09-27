//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
//IRADUKUNDA Epiphanie 223015618package com.SFMS;

//Student.java
import java.time.LocalDate;

public class Student {
 private int studentID;
 private String name;
 private String type;
 private LocalDate startDate;
 private LocalDate endDate;
 private String status;
 
 // Constructors, Getters and Setters
 public Student() {}
 
 public Student(int studentID, String name, String type, LocalDate startDate, LocalDate endDate, String status) {
     this.studentID = studentID;
     this.name = name;
     this.type = type;
     this.startDate = startDate;
     this.endDate = endDate;
     this.status = status;
 }
 
 // Getters and Setters
 public int getStudentID() { return studentID; }
 public void setStudentID(int studentID) { this.studentID = studentID; }
 
 public String getName() { return name; }
 public void setName(String name) { this.name = name; }
 
 public String getType() { return type; }
 public void setType(String type) { this.type = type; }
 
 public LocalDate getStartDate() { return startDate; }
 public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
 
 public LocalDate getEndDate() { return endDate; }
 public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
 
 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }
}


