package com.itroi.itroi.ServiceImplementation;
import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.Model.Cart;
import com.itroi.itroi.Model.Product;
import com.itroi.itroi.ServiceInterfaces.CartService;
import com.itroi.itroi.ServiceInterfaces.ProductService;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.ServiceInterfaces.CartService")
public class CartServiceImpl implements CartService {
    private final Map<Integer, Cart> cartDatabase = new HashMap<>();
    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {this.productService = productService;}

    @Override
    public void addCart(Cart cart) {
        cartDatabase.put(cart.getUserID(), cart);
    }
    @Override
    public void addProductToCart(int userId, int productId) throws ClientFaultException {
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new ClientFaultException("Продукт с ID " + productId + " не знайден");
        }

        Cart cart = cartDatabase.getOrDefault(userId, new Cart());
        cart.setUserID(userId);

        List<Integer> productIDs = cart.getProductIDs() != null ? cart.getProductIDs() : new ArrayList<>();

        if (productIDs.contains(productId)) {
            throw new ClientFaultException("Продукт вже є в кошику");
        }

        productIDs.add(productId);
        cart.setProductIDs(productIDs);
        cartDatabase.put(userId, cart);

        updateTotalAmount(cart);
    }


    @Override
    public Cart getCart(int userId) throws  ClientFaultException {
        Cart cart = cartDatabase.get(userId);
        if (cart == null) {
            throw new ClientFaultException("Кошик для користувача з ID " + userId + " не знайден.");
        }
        return cart;
    }


    @Override
    public void removeProductFromCart(int userId, int productId) throws ClientFaultException {
        /*Cart cart = cartDatabase.get(userId);
        if (cart != null) {
            List<Integer> productIDs = cart.getProductIDs();
            if (productIDs != null) {
                productIDs.removeIf(id -> id == productId);
                cart.setProductIDs(productIDs);
                updateTotalAmount(cart);
            }
        }*/
        Cart cart = cartDatabase.get(userId);

        if (cart == null) {
            throw new ClientFaultException("Корзина для пользователя с ID " + userId + " не найдена.");
        }

        List<Integer> productIDs = cart.getProductIDs();

        if (productIDs == null || !productIDs.contains(productId)) {
            throw new ClientFaultException("Продукт з ID " + productId + " не знайден у кошику користувача с ID " + userId + ".");
        }

        productIDs.removeIf(id -> id == productId);
        cart.setProductIDs(productIDs);
        updateTotalAmount(cart);
    }


    @Override
    public void checkout(int userId)  {
        //later functional
        //need to add new atribute status for cart
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
    public void updateCartStatus(int cartId, String status) {
        // later
    }

    private void updateTotalAmount(Cart cart) throws ClientFaultException {
        double totalAmount = 0.0;

        List<Integer> productIDs = cart.getProductIDs();
        if (productIDs != null) {
            for (Integer productId : productIDs) {
                Product product = productService.getProductById(productId);
                if (product != null) {
                    totalAmount += product.getPrice();
                }
            }
        }
        cart.setTotalAmount(totalAmount);
        System.out.println("Загальна сума кошику оновлена на: " + totalAmount);
    }
    }
