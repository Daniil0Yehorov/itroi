package com.itroi.itroi.ClientTest;

import com.itroi.itroi.ServiceInterfaces.CartService;
import jakarta.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;

import com.itroi.itroi.Model.Cart;


public class testCartService {
    public static void main(String[] args) {
        try {
            URL wsdlURL = new URL("http://localhost:8081/ws/carts?wsdl");

            QName qname = new QName("http://ServiceImplementation.itroi.itroi.com/", "CartServiceImplService");
            Service service = Service.create(wsdlURL, qname);

            CartService cartService = service.getPort(CartService.class);

            Cart retrievedCart = cartService.getCart(1);
            System.out.println("Отриманий кошик: " + retrievedCart);

            cartService.addProductToCart(1, 101);
            System.out.println("Продукт 101 додано до кошика користувача 1.");

            cartService.removeProductFromCart(1, 101);
            System.out.println("Продукт 101 видалено з кошика користувача 1.");

            retrievedCart = cartService.getCart(1);
            System.out.println("Оновлений кошик: " + retrievedCart);

            cartService.checkout(1);
            System.out.println("Замовлення для користувача 1 завершено.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
