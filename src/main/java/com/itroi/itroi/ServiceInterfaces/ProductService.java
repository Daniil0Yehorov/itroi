package com.itroi.itroi.ServiceInterfaces;

import com.itroi.itroi.Model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Отримання списку всіх продуктів з фільтрацією і сортуванням
     * (по категорії, бренду, ціні, наявності).
     *
     * @return список продуктів
     */
    List<Product> getAllProducts();

    /**
     * Отримання детальної інформації про продукт по його ID.
     *
     * @param productId ідентифікатор продукту
     * @return об'єкт Product з детальною інформацією
     */
    Product getProductById(int productId);

    /**
     * Додавання нового продукту (для адміністратора).
     *
     * @param product об'єкт Product, що представляє новий продукт
     * @return об'єкт Product, що представляє доданий продукт
     */
    Product addProduct(Product product);

    /**
     * Редагування даних про продукт (для адміністратора).
     *
     * @param product об'єкт Product з оновленими даними
     */
    void updateProduct(Product product);

    /**
     * Видалення продукту з бази даних (для адміністратора).
     *
     * @param productId ідентифікатор продукту
     */
    void deleteProduct(int productId);
}
