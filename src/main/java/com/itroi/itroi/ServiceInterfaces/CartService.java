package com.itroi.itroi.ServiceInterfaces;

import com.itroi.itroi.Model.Cart;

import java.util.List;

public interface CartService {

    /**
     * Додавання продукту в корзину користувача.
     * @param userId ідентифікатор користувача
     * @param productId ідентифікатор продукту
     * @param quantity кількість продукту для додавання
     */
    void addProductToCart(int userId, int productId, int quantity);

    /**
     * Отримання поточного стану корзини користувача.
     * @param userId ідентифікатор користувача
     * @return об'єкт Cart, що містить дані про корзину
     */
    Cart getCart(int userId);

    /**
     * Видалення продукту з корзини.
     * @param userId ідентифікатор користувача
     * @param productId ідентифікатор продукту
     */
    void removeProductFromCart(int userId, int productId);

    /**
     * Завершення і підтвердження замовлення.
     * @param userId ідентифікатор користувача
     */
    void checkout(int userId);

    /**
     * Отримання всіх замовлень користувачів.
     * @return список усіх замовлень
     */
    List<Cart> getAllCarts();

    /**
     * Отримання детальної інформації про замовлення за ID.
     * @param cartId ідентифікатор замовлення
     * @return об'єкт Cart, що містить дані про замовлення
     */
    Cart getCartById(int cartId);

    /**
     * Оновлення статусу замовлення (наприклад, "В обробці", "Доставлене").
     * @param cartId ідентифікатор замовлення
     * @param status новий статус замовлення
     */
    void updateOrderStatus(int cartId, String status);

}
