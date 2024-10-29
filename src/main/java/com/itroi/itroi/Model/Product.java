package com.itroi.itroi.Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product")
public class Product {
    private int ID;
    private String Name;
    private String Description;
    private double Price;
    private String Category;
    private int CountInStock;

    @XmlElement(name = "ID")
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    @XmlElement(name = "Name")
    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    @XmlElement(name = "Description")
    public String getDescription() { return Description; }
    public void setDescription(String description) { this.Description = description; }

    @XmlElement(name = "Price")
    public double getPrice() { return Price; }
    public void setPrice(double price) { this.Price = price; }

    @XmlElement(name = "Category")
    public String getCategory() { return Category; }
    public void setCategory(String category) { this.Category = category; }

    @XmlElement(name = "CountInStock")
    public int getCountInStock() { return CountInStock; }
    public void setCountInStock(int countInStock) { this.CountInStock = countInStock; }

    public Product(int ID, String name, String description, double price, String category, int countInStock) {
        this.ID = ID;
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.Category = category;
        this.CountInStock = countInStock;
    }
    public Product(){}
}