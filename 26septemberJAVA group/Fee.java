package com.SFMS;
import java.time.LocalDateTime;

public class Fee {
    private int feesID;
    private String name;
    private String description;
    private String attribute1;
    private String attribute2;
    private LocalDateTime createdAt;
    
    public Fee() {}
    
    public Fee(int feesID, String name, String description, String attribute1, String attribute2, LocalDateTime createdAt) {
        this.feesID = feesID;
        this.name = name;
        this.description = description;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.createdAt = createdAt;
    }
    
    public int getFeesID() { return feesID; }
    public void setFeesID(int feesID) { this.feesID = feesID; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getAttribute1() { return attribute1; }
    public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }
    
    public String getAttribute2() { return attribute2; }
    public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}