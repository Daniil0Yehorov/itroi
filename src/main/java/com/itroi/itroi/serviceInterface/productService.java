package com.itroi.itroi.serviceInterface;

import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.generated_models.Product;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;
@WebService(targetNamespace = "http://serviceimpl.itroi.itroi.com/")
public interface productService {

    /**
     * Отримання списку всіх продуктів з фільтрацією і сортуванням
     * (по категорії, бренду, ціні, наявності).
     * @throws Exception якщо виникає помилка.
     * @return список продуктів
     */
    @WebMethod
    List<Product> getAllProducts()throws  Exception;

    /**
     * Отримання детальної інформації про продукт по його ID.
     *
     * @param productId ідентифікатор продукту
     * @return об'єкт Product з детальною інформацією
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    Product getProductById(int productId)throws ClientFaultException;
    /**
     * Додавання нового продукту (для адміністратора).
     *
     * @param product об'єкт Product, що представляє новий продукт
     * @return об'єкт Product, що представляє доданий продукт
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    Product addProduct(Product product)throws ClientFaultException;

    /**
     * Редагування даних про продукт (для адміністратора).
     *
     * @param product об'єкт Product з оновленими даними
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void updateProduct(Product product)throws ClientFaultException;

    /**
     * Видалення продукту з бази даних (для адміністратора).
     *
     * @param productId ідентифікатор продукту
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void deleteProduct(int productId)throws ClientFaultException;
    /**
     * генерація ключа для продукту
     */
    @WebMethod
    int generateUniqueProductId();
}
