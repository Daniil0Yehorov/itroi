package com.itroi.itroi.serviceimpl;


import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.generated_models.Cart;
import com.itroi.itroi.serviceInterface.cartService;
import com.itroi.itroi.generated_models.User;
import com.itroi.itroi.serviceInterface.userService;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.serviceInterface.userService")
public class userImplementation implements userService {
    private final Map<Integer, User> userDatabase = new HashMap<>();
    private final cartService CService;

    public userImplementation (cartService CService) {this.CService = CService;}
    @Override
    public User getUserById(int userId) throws ClientFaultException {
        User user = userDatabase.get(userId);
        if (user == null) {
            throw new ClientFaultException("Користувача з ID " + userId + " не знайдено.");
        }
        return user;
    }

    @Override
    public void updateUser(int userId, User user) throws ClientFaultException {
        if (!userDatabase.containsKey(userId)) {
            throw new ClientFaultException("Користувача з ID " + userId + " не існує. Оновлення неможливе.");
        }
        userDatabase.put(userId, user);
    }

    @Override
    public void createUser(User user) throws ClientFaultException {
        if (userDatabase.containsKey(user.getID())) {
            throw new ClientFaultException("Користувач з ID " + user.getID() + " вже існує. Створення неможливе.");
        }
        userDatabase.put(user.getID(), user);

        Cart cart = new Cart();

        cart.setUserID(user.getID());
        Cart.ProductIDs productIDs = new Cart.ProductIDs();
        productIDs.setProductID(new ArrayList<>());
        cart.setTotalAmount(0.0);
        cart.setStatus("Не оформлений кошик");

        //CService.addCart(cart);
        try {
            CService.addCart(cart);
        } catch (Exception e) {
            throw new ClientFaultException("Помилка при створенні кошика для користувача з ID " + user.getID() + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userId) throws ClientFaultException {
        if (!userDatabase.containsKey(userId)) {
            throw new ClientFaultException("Користувача з ID " + userId + " не знайдено. Видалення неможливе.");
        }
        userDatabase.remove(userId);
    }

    @Override
    public User getUserforAuth(String password, String login) throws ClientFaultException {
        for (User user : userDatabase.values()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new ClientFaultException("Неправильний логін або пароль.");
    }

    @Override
    public int generateUniqueUserId() {
        int userId = 1;
        while (userDatabase.containsKey(userId)) {
            userId++;
        }
        return userId;
    }
}
