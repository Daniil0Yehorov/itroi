package com.itroi.itroi.ServiceImplementation;

import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.Model.User;
import com.itroi.itroi.Model.Cart;
import com.itroi.itroi.ServiceInterfaces.CartService;
import com.itroi.itroi.ServiceInterfaces.UserService;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.ServiceInterfaces.UserService")
public class UserServiceImpl implements UserService {
    private final Map<Integer, User> userDatabase = new HashMap<>();
    private final CartService cartService;

    public UserServiceImpl(CartService cartService) {this.cartService = cartService;}

    @Override
    public User getUserById(int userId) throws ClientFaultException {
        User user = userDatabase.get(userId);
        if (user == null) {
            throw new ClientFaultException("Користувача з ID " + userId + " не знайдено.");
        }
        return user;
    }
    @Override
    public  int generateUniqueUserId() {
        int userId = 1;
        while (userDatabase.containsKey(userId)) {
            userId++;
        }
        return userId;

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

        // При реєстрації користувача,для нього створюється його кошик персоналізований
        Cart cart = new Cart();

        cart.setUserID(user.getID());
        cart.setProductIDs(new ArrayList<>());
        cart.setTotalAmount(0.0);
        cart.setStatus("Не оформлений кошик");

        cartService.addCart(cart);
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
}