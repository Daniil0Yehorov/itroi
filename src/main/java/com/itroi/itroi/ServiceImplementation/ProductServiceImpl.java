package com.itroi.itroi.ServiceImplementation;

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

    @WebMethod
    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productDatabase.values());
    }

    @WebMethod
    @Override
    public Product getProductById(int productId) {
        return productDatabase.get(productId);
    }

    @WebMethod
    @Override
    public Product addProduct(Product product) {
        productDatabase.put(product.getID(), product);
        return product;
    }

    @WebMethod
    @Override
    public void updateProduct(Product product) {
        productDatabase.put(product.getID(), product);
    }

    @WebMethod
    @Override
    public void deleteProduct(int productId) {
        productDatabase.remove(productId);
    }
}