package com.itroi.itroi.Parsers;

import  com.itroi.itroi.Model.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    private String xmlFilePath;

    public DOMParser(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public StoreData demarshal() {
        StoreData storeData = new StoreData();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xmlFilePath));

            // Парсимо Products
            NodeList productNodes = doc.getElementsByTagName("Product");
            Products products = new Products();
            List<Product> productList = new ArrayList<>();
            for (int i = 0; i < productNodes.getLength(); i++) {
                Element productElement = (Element) productNodes.item(i);
                Product product = new Product();

                NodeList idNodes = productElement.getElementsByTagName("ID");
                if (idNodes.getLength() > 0) {
                    product.setID(Integer.parseInt(idNodes.item(0).getTextContent()));
                }
                NodeList nameNodes = productElement.getElementsByTagName("Name");
                if (nameNodes.getLength() > 0) {
                    product.setName(nameNodes.item(0).getTextContent());
                }
                NodeList descriptionNodes = productElement.getElementsByTagName("Description");
                if (descriptionNodes.getLength() > 0) {
                    product.setDescription(descriptionNodes.item(0).getTextContent());
                }
                NodeList priceNodes = productElement.getElementsByTagName("Price");
                if (priceNodes.getLength() > 0) {
                    product.setPrice(Double.parseDouble(priceNodes.item(0).getTextContent()));
                }
                NodeList categoryNodes = productElement.getElementsByTagName("Category");
                if (categoryNodes.getLength() > 0) {
                    product.setCategory(categoryNodes.item(0).getTextContent());
                }
                NodeList countInStockNodes = productElement.getElementsByTagName("CountInStock");
                if (countInStockNodes.getLength() > 0) {
                    product.setCountInStock(Integer.parseInt(countInStockNodes.item(0).getTextContent()));
                }

                productList.add(product);
            }
            products.setProductList(productList);
            storeData.setProducts(products);

            // Парсимо Carts
            NodeList cartNodes = doc.getElementsByTagName("Cart");
            Carts carts = new Carts();
            List<Cart> cartList = new ArrayList<>();
            for (int i = 0; i < cartNodes.getLength(); i++) {
                Element cartElement = (Element) cartNodes.item(i);
                Cart cart = new Cart();

                // Убираем ID корзины, используем только UserID
                NodeList userIdNodes = cartElement.getElementsByTagName("UserID");
                if (userIdNodes.getLength() > 0) {
                    cart.setUserID(Integer.parseInt(userIdNodes.item(0).getTextContent()));
                }

                // Обработка списку айдішок продукту
                NodeList productIdsWrapper = cartElement.getElementsByTagName("ProductIDs");
                if (productIdsWrapper.getLength() > 0) {
                    Element productIdsElement = (Element) productIdsWrapper.item(0);
                    NodeList productIdNodes = productIdsElement.getElementsByTagName("ProductID");
                    List<Integer> productIDs = new ArrayList<>();
                    for (int j = 0; j < productIdNodes.getLength(); j++) {
                        productIDs.add(Integer.parseInt(productIdNodes.item(j).getTextContent()));
                    }
                    cart.setProductIDs(productIDs);
                }

                NodeList totalAmountNodes = cartElement.getElementsByTagName("TotalAmount");
                if (totalAmountNodes.getLength() > 0) {
                    cart.setTotalAmount(Double.parseDouble(totalAmountNodes.item(0).getTextContent()));
                }

                NodeList statusNodes = cartElement.getElementsByTagName("status");
                if (statusNodes.getLength() > 0) {
                    cart.setStatus(statusNodes.item(0).getTextContent());
                }

                cartList.add(cart);
            }
            carts.setCartList(cartList);
            storeData.setCarts(carts);

            // Парсимо Users
            NodeList userNodes = doc.getElementsByTagName("User");
            Users users = new Users();
            List<User> userList = new ArrayList<>();
            for (int i = 0; i < userNodes.getLength(); i++) {
                Element userElement = (Element) userNodes.item(i);
                User user = new User();

                NodeList userIdNodes = userElement.getElementsByTagName("ID");
                if (userIdNodes.getLength() > 0) {
                    user.setID(Integer.parseInt(userIdNodes.item(0).getTextContent()));
                }
                NodeList typeNodes = userElement.getElementsByTagName("Type");
                if (typeNodes.getLength() > 0) {
                    user.setType(typeNodes.item(0).getTextContent());
                }
                NodeList loginNodes = userElement.getElementsByTagName("Login");
                if (loginNodes.getLength() > 0) {
                    user.setLogin(loginNodes.item(0).getTextContent());
                }
                NodeList passwordNodes = userElement.getElementsByTagName("Password");
                if (passwordNodes.getLength() > 0) {
                    user.setPassword(passwordNodes.item(0).getTextContent());
                }
                NodeList nameNodes = userElement.getElementsByTagName("Name");
                if (nameNodes.getLength() > 0) {
                    user.setName(nameNodes.item(0).getTextContent());
                }
                NodeList phoneNodes = userElement.getElementsByTagName("Phone");
                if (phoneNodes.getLength() > 0) {
                    user.setPhone(phoneNodes.item(0).getTextContent());
                }
                NodeList emailNodes = userElement.getElementsByTagName("Email");
                if (emailNodes.getLength() > 0) {
                    user.setEmail(emailNodes.item(0).getTextContent());
                }

                userList.add(user);
            }
            users.setUserList(userList);
            storeData.setUsers(users);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return storeData;
    }

    public void marshal(StoreData storeData, String outputPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element storeElement = doc.createElement("StoreData");
            doc.appendChild(storeElement);

            // Запис продуктів
            Element productsElement = doc.createElement("Products");
            for (Product product : storeData.getProducts().getProductList()) {
                Element productElement = doc.createElement("Product");
                productElement.appendChild(createElement(doc, "ID", String.valueOf(product.getID())));
                productElement.appendChild(createElement(doc, "Name", product.getName()));
                productElement.appendChild(createElement(doc, "Description", product.getDescription()));
                productElement.appendChild(createElement(doc, "Price", String.valueOf(product.getPrice())));
                productElement.appendChild(createElement(doc, "Category", product.getCategory()));
                productElement.appendChild(createElement(doc, "CountInStock", String.valueOf(product.getCountInStock())));
                productsElement.appendChild(productElement);
            }
            storeElement.appendChild(productsElement);

            // Запис кошику
            Element cartsElement = doc.createElement("Carts");
            for (Cart cart : storeData.getCarts().getCartList()) {
                Element cartElement = doc.createElement("Cart");
                cartElement.appendChild(createElement(doc, "UserID", String.valueOf(cart.getUserID())));

                Element productIDsElement = doc.createElement("ProductIDs");
                for (Integer productID : cart.getProductIDs()) {
                    productIDsElement.appendChild(createElement(doc, "ProductID", String.valueOf(productID)));
                }
                cartElement.appendChild(productIDsElement);
                cartElement.appendChild(createElement(doc, "TotalAmount", String.valueOf(cart.getTotalAmount())));
                cartElement.appendChild(createElement(doc, "status", cart.getStatus()));
                cartsElement.appendChild(cartElement);
            }
            storeElement.appendChild(cartsElement);

            // Запис користувачів
            Element usersElement = doc.createElement("Users");
            for (User user : storeData.getUsers().getUserList()) {
                Element userElement = doc.createElement("User");
                userElement.appendChild(createElement(doc, "ID", String.valueOf(user.getID())));
                userElement.appendChild(createElement(doc, "Type", user.getType()));
                userElement.appendChild(createElement(doc, "Login", user.getLogin()));
                userElement.appendChild(createElement(doc, "Password", user.getPassword()));
                userElement.appendChild(createElement(doc, "Name", user.getName()));
                userElement.appendChild(createElement(doc, "Phone", user.getPhone()));
                if (user.getEmail() != null) {
                    userElement.appendChild(createElement(doc, "Email", user.getEmail()));
                }
                usersElement.appendChild(userElement);
            }
            storeElement.appendChild(usersElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(outputPath));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
    public static void main(String[] args) {
        //with normal data
        DOMParser domParser = new DOMParser("C:/Users/Даниил/Рабочий стол/itroi/src/main/resources/static/xml/Full.xml");
        System.out.println("--== DOM Parser ==--");
        StoreData storeData = domParser.demarshal();

        // check fast
        for (Product product : storeData.getProducts().getProductList()) {
            System.out.println("Product Name: " + product.getName());
        }

        domParser.marshal(storeData, "outputDOM.xml");

        //with invalid data
        DOMParser domParser1 = new DOMParser("C:/Users/Даниил/Рабочий стол/itroi/invalid_storeData.xml");

        StoreData storeData1 = domParser1.demarshal();
        //не спрацює через інвалідний файл
        domParser.marshal(storeData1, "outputDOM1.xml");
    }
}
