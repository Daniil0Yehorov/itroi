package com.itroi.itroi.Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User {
    private int ID;
    private String Type;
    private String Login;
    private String Password;
    private String Name;
    private String Phone;
    private String Email;

    // Getters and Setters
    @XmlElement(name = "ID")
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    @XmlElement(name = "Type")
    public String getType() { return Type; }
    public void setType(String type) { this.Type = type; }

    @XmlElement(name = "Login")
    public String getLogin() { return Login; }
    public void setLogin(String login) { this.Login = login; }

    @XmlElement(name = "Password")
    public String getPassword() { return Password; }
    public void setPassword(String password) { this.Password = password; }

    @XmlElement(name = "Name")
    public String getName() { return Name; }
    public void setName(String name) { this.Name = name; }

    @XmlElement(name = "Phone")
    public String getPhone() { return Phone; }
    public void setPhone(String phone) { this.Phone = phone; }

    @XmlElement(name = "Email")
    public String getEmail() { return Email; }
    public void setEmail(String email) { this.Email = email; }

    public User(int ID, String type, String login, String password, String name, String phone, String email) {
        this.ID = ID;
        Type = type;
        Login = login;
        Password = password;
        Name = name;
        Phone = phone;
        Email = email;
    }
    public User() {}
}