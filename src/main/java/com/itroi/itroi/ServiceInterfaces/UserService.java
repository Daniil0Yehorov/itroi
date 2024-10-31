package com.itroi.itroi.ServiceInterfaces;

import com.itroi.itroi.Exception.ClientFaultException;
import com.itroi.itroi.Model.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(targetNamespace = "http://ServiceImplementation.itroi.itroi.com/")
public interface UserService {

    /**
     * Отримання інформації про користувача.
     * @param userId ідентифікатор користувача
     * @return об'єкт User, що містить інформацію про користувача
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    User getUserById(int userId)throws ClientFaultException;

    /**
     * Оновлення даних користувача (наприклад, зміна пароля або контактної інформації).
     * @param userId ідентифікатор користувача
     * @param user об'єкт User з оновленими даними
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void updateUser(int userId, User user)throws ClientFaultException;

    /**
     * Реєстрація нового користувача.
     * @param user об'єкт User, що представляє нового користувача
     * @return об'єкт User, що представляє зареєстрованого користувача
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void createUser(User user)throws ClientFaultException;

    /**
     * Видалення користувача (за необхідності, для адміністратора).
     * @param userId ідентифікатор користувача
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    void deleteUser(int userId)throws ClientFaultException;
    /**
     * Отримання користувача за паролем та логіном задля авторизації.
     * @param password пароль користувача
     * @param login логін користувача
     * @throws ClientFaultException якщо виникає помилка.
     */
    @WebMethod
    User getUserforAuth(String password, String login)throws ClientFaultException;
    /**
     * генерація унікального ключа для користувача
     */
    @WebMethod
    int generateUniqueUserId();

}
