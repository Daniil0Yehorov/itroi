package com.itroi.itroi.parser;

import com.itroi.itroi.generated_models.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class saxParser extends DefaultHandler {
    private String currentElement;
    private List<Product> products;
    private List<Cart> carts;
    private List<User> users;

    private Product currentProduct;
    private Cart currentCart;
    private User currentUser;
    private Cart.ProductIDs productIDs;

    private String currentParentElement;

    public List<Product> getProducts() {
        return products;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void parse(InputStream in, Schema schema) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setSchema(schema);
        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(in, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = localName;

        switch (currentElement) {
            case "Products":
                products = new ArrayList<>();
                currentParentElement = "Products";
                break;
            case "Product":
                currentProduct = new Product();
                currentParentElement = "Product";
                break;
            case "Carts":
                carts = new ArrayList<>();
                currentParentElement = "Carts";
                break;
            case "Cart":
                currentCart = new Cart();
                productIDs = new Cart.ProductIDs();
                currentParentElement = "Cart";
                break;
            case "Users":
                users = new ArrayList<>();
                currentParentElement = "Users";
                break;
            case "User":
                currentUser = new User();
                currentParentElement = "User";
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if (value.isEmpty()) {
            return;
        }

        switch (currentElement) {
            case "ID":
                if ("Product".equals(currentParentElement)) {
                    currentProduct.setID(Integer.parseInt(value));
                }  else if ("User".equals(currentParentElement)) {
                    currentUser.setID(Integer.parseInt(value));
                }
                break;
            case "Name":
                if ("Product".equals(currentParentElement)) {
                    currentProduct.setName(value);
                }
                else if ("User".equals(currentParentElement)) {
                    currentUser.setName(value);
                }
                break;
            case "Description":
                if ("Product".equals(currentParentElement)) {
                    currentProduct.setDescription(value);
                }
                break;
            case "Price":
                if ("Product".equals(currentParentElement)) {
                    currentProduct.setPrice(Double.parseDouble(value));
                }
                break;
            case "Category":
                if ("Product".equals(currentParentElement)) {
                    currentProduct.setCategory(value);
                }
                break;
            case "CountInStock":
                if ("Product".equals(currentParentElement)) {
                    currentProduct.setCountInStock(Integer.parseInt(value));
                }
                break;
            case "UserID":
                if ("Cart".equals(currentParentElement)) {
                    currentCart.setUserID(Integer.parseInt(value));
                }
                break;
            case "ProductID":
                if ("Cart".equals(currentParentElement) && productIDs != null) {
                    productIDs.getProductID().add(Integer.parseInt(value));
                }
                break;
            case "TotalAmount":
                if ("Cart".equals(currentParentElement)) {
                    currentCart.setTotalAmount(Double.parseDouble(value));
                }
                break;
            case "status":
                if ("Cart".equals(currentParentElement)) {
                    currentCart.setStatus(value);
                }
                break;
            case "Type":
                if ("User".equals(currentParentElement)) {
                    currentUser.setType(value);
                }
                break;
            case "Login":
                if ("User".equals(currentParentElement)) {
                    currentUser.setLogin(value);
                }
                break;
            case "Password":
                if ("User".equals(currentParentElement)) {
                    currentUser.setPassword(value);
                }
                break;
            case "Phone":
                if ("User".equals(currentParentElement)) {
                    currentUser.setPhone(value);
                }
                break;
            case "Email":
                if ("User".equals(currentParentElement)) {
                    currentUser.setEmail(value);
                }
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (localName) {
            case "Product":
                products.add(currentProduct);
                break;
            case "Cart":
                currentCart.setProductIDs(productIDs);
                System.out.println(currentCart.getProductIDs().getProductID());
                carts.add(currentCart);
                break;
            case "User":
                users.add(currentUser);
                break;
            case "Products":
                break;
            case "Carts":
                break;
            case "Users":
                break;
        }
        currentElement = "";
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xsd"));

        System.out.println("--== SAX Parser ==--");
        saxParser parser = new saxParser();
        parser.parse(new FileInputStream("C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xml"), schema);

        List<Product> products = parser.getProducts();
        List<Cart> carts = parser.getCarts();
        List<User> users = parser.getUsers();

        System.out.println("--== Users ==--");
        for (User user : users) {
            System.out.println("ID: " + user.getID());
            System.out.println("Login: " + user.getLogin());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Type: " + user.getType());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Email: " + user.getEmail());
            System.out.println("----------------------------");
        }

        System.out.println("--== Products ==--");
        for (Product product : products) {
            System.out.println("ID: " + product.getID());
            System.out.println("Name: " + product.getName());
            System.out.println("Description: " + product.getDescription());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Category: " + product.getCategory());
            System.out.println("CountInStock: " + product.getCountInStock());
            System.out.println("----------------------------");
        }

        System.out.println("--== Carts ==--");
        for (Cart cart : carts) {
            System.out.println("User ID: " + cart.getUserID());
            List<Integer> productIDs = cart.getProductIDs().getProductID();

            System.out.print("Product IDs: ");
            for (Integer productId : productIDs) {
                System.out.print(productId + " ");

                for (Product product : products) {
                    if (product.getID() == productId) {
                        System.out.println(" (Product Name: " + product.getName() + ")");
                        break;
                    }
                }
            }
            System.out.println();
            System.out.println("Total Amount: " + cart.getTotalAmount());
            System.out.println("Status: " + cart.getStatus());
            System.out.println("----------------------------");
        }
    }
}