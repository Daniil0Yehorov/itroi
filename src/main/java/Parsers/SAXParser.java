package Parsers;

import Model.Cart;
import Model.Product;
import Model.User;
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

public class SAXParser extends DefaultHandler {
    private String currentElement;
    private List<Product> products;
    private List<Cart> carts;
    private List<User> users;

    private Product currentProduct;
    private Cart currentCart;
    private User currentUser;
    private List<Integer> productIDs;

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

        if ("Products".equals(currentElement)) {
            products = new ArrayList<>();
        } else if ("Product".equals(currentElement)) {
            currentProduct = new Product();
        } else if ("Carts".equals(currentElement)) {
            carts = new ArrayList<>();
        } else if ("Cart".equals(currentElement)) {
            currentCart = new Cart();
            productIDs = new ArrayList<>();
        } else if ("Users".equals(currentElement)) {
            users = new ArrayList<>();
        } else if ("User".equals(currentElement)) {
            currentUser = new User();
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
                } else if ("Cart".equals(currentParentElement)) {
                    currentCart.setID(Integer.parseInt(value));
                } else if ("User".equals(currentParentElement)) {
                    currentUser.setID(Integer.parseInt(value));
                }
                break;
            case "Name":
                currentProduct.setName(value);
                break;
            case "Description":
                currentProduct.setDescription(value);
                break;
            case "Price":
                currentProduct.setPrice(Double.parseDouble(value));
                break;
            case "Category":
                currentProduct.setCategory(value);
                break;
            case "CountInStock":
                currentProduct.setCountInStock(Integer.parseInt(value));
                break;
            case "UserID":
                currentCart.setUserID(Integer.parseInt(value));
                break;
            case "ProductID":
                productIDs.add(Integer.parseInt(value));
                break;
            case "TotalAmount":
                currentCart.setTotalAmount(Double.parseDouble(value));
                break;
            case "Login":
                currentUser.setLogin(value);
                break;
            case "Password":
                currentUser.setPassword(value);
                break;
            case "Type":
                currentUser.setType(value);
                break;
            case "Phone":
                currentUser.setPhone(value);
                break;
            case "Email":
                currentUser.setEmail(value);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("Product".equals(localName)) {
            products.add(currentProduct);
        } else if ("Cart".equals(localName)) {
            currentCart.setProductIDs(productIDs);
            carts.add(currentCart);
        } else if ("User".equals(localName)) {
            users.add(currentUser);
        }

        currentParentElement = null;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xsd"));

        System.out.println("--== SAX Parser ==--");
        SAXParser parser = new SAXParser();
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
            System.out.println("Cart ID: " + cart.getID());
            System.out.println("User ID: " + cart.getUserID());
            System.out.println("Product IDs: " + cart.getProductIDs());
            System.out.println("Total Amount: " + cart.getTotalAmount());
            System.out.println("----------------------------");
        }
    }
}