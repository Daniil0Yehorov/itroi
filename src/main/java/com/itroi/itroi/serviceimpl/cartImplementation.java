package com.itroi.itroi.serviceimpl;

import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.generated_models.Product;
import com.itroi.itroi.generated_models.Cart;
import com.itroi.itroi.serviceInterface.cartService;
import com.itroi.itroi.serviceInterface.productService;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.serviceInterface.cartService")
public class cartImplementation implements cartService {
    private final Map<Integer, Cart> cartDatabase = new HashMap<>();
    private final productService PService;

    public cartImplementation(productService PService) {this.PService = PService;}
    @Override
    public void addProductToCart(int userId, int productId) throws ClientFaultException {

        Cart cart=cartDatabase.get(userId);
        Product product = PService.getProductById(productId);
        if (product == null) {
            throw new ClientFaultException("Продукт с ID " + productId + " не знайден");
        }


        List<Integer> productIDs = cart.getProductIDs() != null ? cart.getProductIDs().getProductID() :
                new ArrayList<>();
        if (productIDs.contains(productId)) {
            throw new ClientFaultException("Продукт вже є в кошику");
        }

        productIDs.add(productId);
        cart.getProductIDs().setProductID(productIDs);
        //cartDatabase.put(userId, cart);
        updateTotalAmount(cart);

    }



    @Override
    public Cart getCart(int userId) throws ClientFaultException {
        Cart cart = cartDatabase.get(userId);
        if (cart == null) {
            throw new ClientFaultException("Кошик для користувача з ID " + userId + " не знайден.");
        }
        return cart;
    }

    @Override
    public void addCart(Cart cart) {
        cartDatabase.put(cart.getUserID(), cart);
    }

    @Override
    public void removeProductFromCart(int userId, int productId) throws ClientFaultException {
        Cart cart = cartDatabase.get(userId);
        if (cart == null) {
            throw new ClientFaultException("Корзина для пользователя с ID " + userId + " не найдена.");
        }

        List<Integer> productIDs = cart.getProductIDs().getProductID();
        if (!productIDs.contains(productId)) {
            throw new ClientFaultException("Продукт з ID " + productId + " не знайден у кошику користувача с ID " + userId + ".");
        }

        productIDs.removeIf(id -> id.equals(productId));
        cart.getProductIDs().getProductID().removeAll(productIDs);
        updateTotalAmount(cart);
    }

    @Override
    public Cart checkout(int userId) {
        Cart currentcart=cartDatabase.get(userId);
        currentcart.setStatus("Оформленний кошик");

        return currentcart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return new ArrayList<>(cartDatabase.values());
    }

    @Override
    public Cart getCartById(int userId) {
        return cartDatabase.get(userId);
    }
    private void updateTotalAmount(Cart cart) throws ClientFaultException {
        double totalAmount = 0.0;
        List<Integer> productIDs = cart.getProductIDs().getProductID();
        for (Integer productId : productIDs) {
            Product product = PService.getProductById(productId);
            if (product != null) {
                totalAmount += product.getPrice();
            }
        }
        cart.setTotalAmount(totalAmount);
        System.out.println("Загальна сума кошику оновлена на: " + totalAmount);
    }
}
