package com.SFMS;

//Payment.java
import java.time.LocalDate;

public class Payment {
 private int paymentID;
 private String referenceNo;
 private double amount;
 private LocalDate date;
 private String method;
 private String status;
 
 // Constructors, Getters and Setters
 public Payment() {}
 
 public Payment(int paymentID, String referenceNo, double amount, LocalDate date, String method, String status) {
     this.paymentID = paymentID;
     this.referenceNo = referenceNo;
     this.amount = amount;
     this.date = date;
     this.method = method;
     this.status = status;
 }
 
 // Getters and Setters
 public int getPaymentID() { return paymentID; }
 public void setPaymentID(int paymentID) { this.paymentID = paymentID; }
 
 public String getReferenceNo() { return referenceNo; }
 public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
 
 public double getAmount() { return amount; }
 public void setAmount(double amount) { this.amount = amount; }
 
 public LocalDate getDate() { return date; }
 public void setDate(LocalDate date) { this.date = date; }
 
 public String getMethod() { return method; }
 public void setMethod(String method) { this.method = method; }
 
 public String getStatus() { return status; }
 public void setStatus(String status) { this.status = status; }
}