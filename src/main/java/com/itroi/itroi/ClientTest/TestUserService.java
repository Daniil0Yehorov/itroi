package com.itroi.itroi.ClientTest;

import com.itroi.itroi.ServiceInterfaces.UserService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;
import com.itroi.itroi.Model.User;

public class TestUserService {
    public static void main(String[] args) {
        try {
            URL wsdlURL = new URL("http://localhost:8081/ws/users?wsdl");

            QName qname = new QName("http://ServiceImplementation.itroi.itroi.com/", "UserServiceImplService");
            Service service = Service.create(wsdlURL, qname);

            UserService userService = service.getPort(UserService.class);

            User newUser = new User(1, "користувач", "login123", "password1313", "Іван",
                    "+380988807825", "ivan@example.com");
            userService.createUser(newUser);
            System.out.println("Користувач доданий: " + newUser);

            User retrievedUser = userService.getUserById(1);
            System.out.println("Отриманий користувач: " + retrievedUser);

            newUser.setEmail("ivan_new@example.com");
            userService.updateUser(1, newUser);
            System.out.println("Користувач оновлений: " + newUser);

            userService.deleteUser(1);
            System.out.println("Користувач видалений: " + newUser.getID());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}