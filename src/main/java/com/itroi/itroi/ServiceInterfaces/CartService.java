package com.itroi.itroi.ServiceInterfaces;

import com.itroi.itroi.Model.Cart;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;
@WebService(targetNamespace = "http://ServiceImplementation.itroi.itroi.com/")
public interface CartService {

    /**
     * Додавання продукту в корзину користувача.
     * @param userId ідентифікатор користувача
     * @param productId ідентифікатор продукту
     * @param quantity кількість продукту для додавання
     */
    @WebMethod
    void addProductToCart(int userId, int productId, int quantity);

    /**
     * Отримання поточного стану корзини користувача.
     * @param userId ідентифікатор користувача
     * @return об'єкт Cart, що містить дані про корзину
     */
    @WebMethod
    Cart getCart(int userId);

    /**
     * Видалення продукту з корзини.
     * @param userId ідентифікатор користувача
     * @param productId ідентифікатор продукту
     */
    @WebMethod
    void removeProductFromCart(int userId, int productId);

    /**
     * Завершення і підтвердження замовлення.
     * @param userId ідентифікатор користувача
     */
    @WebMethod
    void checkout(int userId);

    /**
     * Отримання всіх замовлень користувачів.
     * @return список усіх замовлень
     */
    @WebMethod
    List<Cart> getAllCarts();

    /**
     * Отримання детальної інформації про замовлення за ID.
     * @param cartId ідентифікатор замовлення
     * @return об'єкт Cart, що містить дані про замовлення
     */
    @WebMethod
    Cart getCartById(int cartId);

    /**
     * Оновлення статусу замовлення (наприклад, "В обробці", "Доставлене").
     * @param cartId ідентифікатор замовлення
     * @param status новий статус замовлення
     */
    @WebMethod
    void updateOrderStatus(int cartId, String status);

}
