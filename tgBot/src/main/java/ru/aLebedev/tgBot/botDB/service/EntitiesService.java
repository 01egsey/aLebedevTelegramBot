package ru.aLebedev.tgBot.botDB.service;

import java.util.List;

import ru.aLebedev.tgBot.botDB.entity.Category;
import ru.aLebedev.tgBot.botDB.entity.Client;
import ru.aLebedev.tgBot.botDB.entity.ClientOrder;
import ru.aLebedev.tgBot.botDB.entity.Product;

/**
 * Сервис для работы с сущностями телеграмм-бота
 */
public interface EntitiesService {

    /**
     * Получить список подкатегорий для конкретной категории по id
     * 
     * @param id идентификатор категории
     */
    ClientOrder findClientOrderByClient_Id(Long id);

    /**
     * Найти продукт по его id
     * 
     * 
     */
    Product findProductById(Long id);

    /**
     * Создание новой записи о клиенте
     * 
     * 
     */
    Client createClient(Client client);

    /**
     * Получить список корневых категорий
     * 
     * 
     */
    List<Category> findRootCategories();

    /**
     * Получить список подкатегорий для конкретной категории по id
     * 
     * @param id идентификатор категории
     */
    List<Category> findCategoriesByParentId(Long id);

    /**
     * Получить список товаров в категории
     * 
     * @param id идентификатор категории
     */
    List<Product> getProductsByCategoryId(Long id);

    /**
     * Получить список заказов клиента
     * 
     * @param id идентификатор клиента
     */
    List<ClientOrder> getClientOrders(Long id);

    /**
     * Получить список всех товаров во всех заказах клиента
     * 
     * @param id идентификатор клиента
     */
    List<Product> getClientProducts(Long id);

    /**
     * Получить указанное кол-во самых популярных (наибольшее
     * количество штук в заказах) товаров среди клиентов
     * 
     * @param limit максимальное кол-во товаров
     */
    List<Product> getTopPopularProducts(Integer limit);

    /**
     * Найти всех клиентов по подстроке имени
     * 
     * @param name подстрока имени клиента
     */
    default List<Client> searchClientsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }

    /**
     * Найти все продукты по подстроке названия
     * 
     * @param name подстрока названия продукта
     */
    default List<Product> searchProductsByName(String name) {
        throw new UnsupportedOperationException("Доп. задание");
    }
}