package com.itroi.itroi.ServiceImplementation;

import com.itroi.itroi.Model.Cart;
import com.itroi.itroi.ServiceInterfaces.CartService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.ServiceInterfaces.CartService")
public class CartServiceImpl implements CartService {
    private final Map<Integer, Cart> cartDatabase = new HashMap<>();


    @Override
    public void addProductToCart(int userId, int productId, int quantity) {
        Cart cart = cartDatabase.getOrDefault(userId, new Cart());
        cart.setUserID(userId);
        List<Integer> productIDs = cart.getProductIDs() != null ? cart.getProductIDs() : new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            productIDs.add(productId);
        }

        cart.setProductIDs(productIDs);
        cartDatabase.put(userId, cart);
        updateTotalAmount(cart);
    }


    @Override
    public Cart getCart(int userId) {
        return cartDatabase.getOrDefault(userId, new Cart());
    }


    @Override
    public void removeProductFromCart(int userId, int productId) {
        Cart cart = cartDatabase.get(userId);
        if (cart != null) {
            List<Integer> productIDs = cart.getProductIDs();
            if (productIDs != null) {
                productIDs.removeIf(id -> id == productId);
                cart.setProductIDs(productIDs);
                updateTotalAmount(cart);
            }
        }
    }


    @Override
    public void checkout(int userId) {
       //later
    }


    @Override
    public List<Cart> getAllCarts() {
        return new ArrayList<>(cartDatabase.values());
    }


    @Override
    public Cart getCartById(int cartId) {
        return cartDatabase.get(cartId);
    }


    @Override
    public void updateOrderStatus(int cartId, String status) {
        // later
    }

    private void updateTotalAmount(Cart cart) {
        //later
    }
}