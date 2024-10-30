package com.itroi.itroi.ServiceImplementation;

import com.itroi.itroi.Model.User;
import com.itroi.itroi.ServiceInterfaces.UserService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.ServiceInterfaces.UserService")
public class UserServiceImpl implements UserService {
    private final Map<Integer, User> userDatabase = new HashMap<>();

    @WebMethod
    @Override
    public User getUserById(int userId) {
        return userDatabase.get(userId);
    }

    @WebMethod
    @Override
    public void updateUser(int userId, User user) {
        userDatabase.put(userId, user);
    }

    @WebMethod
    @Override
    public User createUser(User user) {
        userDatabase.put(user.getID(), user);
        return user;
    }

    @WebMethod
    @Override
    public void deleteUser(int userId) {
        userDatabase.remove(userId);
    }
    @WebMethod
    @Override
    public User getUserforAuth(String password,String login){
        for (User user : userDatabase.values()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;//no user
    }
}