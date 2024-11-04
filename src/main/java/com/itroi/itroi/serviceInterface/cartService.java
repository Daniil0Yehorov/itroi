package com.itroi.itroi.serviceInterface;
import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.generated_models.Cart;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;
@WebService(targetNamespace = "http://serviceimpl.itroi.itroi.com/")
public interface cartService {
    /**
     * Додавання продукту в корзину користувача.
     * @param userId ідентифікатор користувача
     * @param productId ідентифікатор продукту
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void addProductToCart(int userId, int productId)throws ClientFaultException;;

    /**
     * Отримання поточного стану корзини користувача.
     * @param userId ідентифікатор користувача
     * @return об'єкт Cart, що містить дані про корзину
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    Cart getCart(int userId)throws  ClientFaultException;
    /**
     * Додає нову корзину для користувача.
     *
     * @param cart об'єкт Cart, що містить дані про корзину, яка буде додана.
     */
    @WebMethod
    void addCart(Cart cart);

    /**
     * Видалення продукту з корзини.
     * @param userId ідентифікатор користувача
     * @param productId ідентифікатор продукту
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void removeProductFromCart(int userId, int productId)throws ClientFaultException;

    /**
     * Завершення і підтвердження замовлення(кошика)
     * @return повертатиме повний об'єкт кошика.
     * @param userId ідентифікатор користувача
     */
    @WebMethod
    Cart checkout(int userId);

    /**
     * Отримання всіх замовлень користувачів.
     * @return список усіх замовлень
     */
    @WebMethod
    List<Cart> getAllCarts();

    /**
     * Отримання детальної інформації про замовлення за ID.
     * @param userId ідентифікатор користувача
     * @return об'єкт Cart, що містить дані про замовлення
     */
    @WebMethod
    Cart getCartById(int userId);

}
