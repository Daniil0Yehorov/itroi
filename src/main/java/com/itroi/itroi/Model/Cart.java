package com.itroi.itroi.Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.List;

@XmlRootElement(name = "Cart")
public class Cart {
    private int UserID;
    private List<Integer> ProductIDs;
    private double TotalAmount;
    private String status;

    // Getters and Setters
    @XmlElement(name = "UserID")
    public int getUserID() {
        return UserID;
    }
    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    @XmlElementWrapper(name = "ProductIDs")
    @XmlElement(name = "ProductID")
    public List<Integer> getProductIDs() {
        return ProductIDs;
    }

    public void setProductIDs(List<Integer> productIDs) {
        this.ProductIDs = productIDs;
    }

    @XmlElement(name = "TotalAmount")
    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.TotalAmount = totalAmount;
    }

    public Cart(int userID, List<Integer> productIDs, double totalAmount,String status) {
        this.UserID = userID;
        this.ProductIDs = productIDs;
        this.TotalAmount = totalAmount;
        this.status = status;
    }

    public Cart() {}
}