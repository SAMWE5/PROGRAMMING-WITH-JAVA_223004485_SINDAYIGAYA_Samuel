//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
//IRADUKUNDA Epiphanie 223015618package com.SFMS;

//FeeStructure.java
import java.time.LocalDate;

public class FeeStructure {
 private int feeStructureID;
 private String title;
 private LocalDate date;
 private String status;
 private double value;
 private String notes;
 
 // Constructors, Getters and Setters
 public FeeStructure() {}
 
 public FeeStructure(int feeStructureID, String title, LocalDate date, String status, double value, String notes) {
     this.feeStructureID = feeStructureID;
     this.title = title;
     this.date = date;
     this.status = status;
     this.value = value;
     this.notes = notes;
 }
 
 // Getters and Setters
 public int getFeeStructureID() { return feeStructureID; }
 public void setFeeStructureID(int feeStructureID) { this.feeStructureID = feeStructureID; }
 
 public String getTitle() { return title; }
 public void setTitle(String title) { this.title = title; }
 
 public LocalDate getDate() { return date; }
 public void setDate(LocalDate date) { this.date = date; }
 
 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }
 
 public double getValue() { return value; }
 public void setValue(double value) { this.value = value; }
 
 public String getNotes() { return notes; }
 public void setNotes(String notes) { this.notes = notes; }
}

