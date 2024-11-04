package com.itroi.itroi.serviceimpl;

import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.generated_models.Product;
import com.itroi.itroi.serviceInterface.productService;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.serviceInterface.productService")
public class productImplementation implements productService {
    private final Map<Integer, Product> productDatabase = new HashMap<>();

    @Override
    public List<Product> getAllProducts()throws  Exception {
        try {
            return new ArrayList<>(productDatabase.values());
        } catch (Exception e) {
            throw new Exception("Помилка на сервері при отриманні всіх продуктів: " + e.getMessage());
        }
    }

    @Override
    public Product getProductById(int productId)throws ClientFaultException {
        Product product = productDatabase.get(productId);
        if (product == null) {
            throw new ClientFaultException("Продукт з ID " + productId + " не знайдено.");
        }
        return product;

    }

    @Override
    public Product addProduct(Product product)throws ClientFaultException {
        if (productDatabase.containsKey(product.getID())) {
            throw new ClientFaultException("Продукт з ID " + product.getID() + " вже існує. Додавання неможливе.");
        }
        productDatabase.put(product.getID(), product);
        return product;
    }

    @Override
    public void updateProduct(Product product)throws ClientFaultException {

        if (!productDatabase.containsKey(product.getID())) {
            throw new ClientFaultException("Продукт з ID " + product.getID() + " не знайдено. Оновлення неможливе.");
        }
        productDatabase.put(product.getID(), product);
    }

    @Override
    public void deleteProduct(int productId) throws ClientFaultException {
        if (!productDatabase.containsKey(productId)) {
            throw new ClientFaultException("Продукт з ID " + productId + " не знайдено. Видалення неможливе.");
        }
        productDatabase.remove(productId);
    }

    @Override
    public int generateUniqueProductId() {
        int productId = 1;

        while (productDatabase.containsKey(productId)) {
            productId++;
        }
        return productId;
    }
}
