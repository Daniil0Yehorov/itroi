package com.itroi.itroi.ServiceImplementation;

import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.Model.Product;
import com.itroi.itroi.ServiceInterfaces.ProductService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "com.itroi.itroi.ServiceInterfaces.ProductService")
public class ProductServiceImpl implements ProductService {
    private final Map<Integer, Product> productDatabase = new HashMap<>();

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDatabase.values());
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
}