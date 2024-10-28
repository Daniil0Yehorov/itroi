package com.itroi.itroi.Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Cart")
public class Cart {
    private int ID;
    private int UserID;
    private List<Integer> ProductIDs;
    private double TotalAmount;

    // Getters and Setters
    @XmlElement(name = "ID")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @XmlElement(name = "UserID")
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    @XmlElementWrapper(name = "ProductIDs")  // "обертка для списку айдішок"
    @XmlElement(name = "ProductID")  // елемент списку
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

    public Cart(int ID, int userID, List<Integer> productIDs, double totalAmount) {
        this.ID = ID;
        UserID = userID;
        ProductIDs = productIDs;
        TotalAmount = totalAmount;
    }
    public Cart(){}
}