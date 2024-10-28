package com.itroi.itroi.ServiceInterfaces;

import com.itroi.itroi.Model.User;

public interface UserService {

    /**
     * Отримання інформації про користувача.
     * @param userId ідентифікатор користувача
     * @return об'єкт User, що містить інформацію про користувача
     */
    User getUserById(int userId);

    /**
     * Оновлення даних користувача (наприклад, зміна пароля або контактної інформації).
     * @param userId ідентифікатор користувача
     * @param user об'єкт User з оновленими даними
     */
    void updateUser(int userId, User user);

    /**
     * Реєстрація нового користувача.
     * @param user об'єкт User, що представляє нового користувача
     * @return об'єкт User, що представляє зареєстрованого користувача
     */
    User createUser(User user);

    /**
     * Видалення користувача (за необхідності, для адміністратора).
     * @param userId ідентифікатор користувача
     */
    void deleteUser(int userId);
}
