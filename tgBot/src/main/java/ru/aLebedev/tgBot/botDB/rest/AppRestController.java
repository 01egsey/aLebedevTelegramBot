package ru.aLebedev.tgBot.botDB.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.aLebedev.tgBot.botDB.entity.Client;
import ru.aLebedev.tgBot.botDB.entity.ClientOrder;
import ru.aLebedev.tgBot.botDB.entity.Product;
import ru.aLebedev.tgBot.botDB.service.EntitiesServiceImpl;

@RestController
public class AppRestController {

    private final EntitiesServiceImpl appService;

    public AppRestController(EntitiesServiceImpl appService) {
        this.appService = appService;
    }

    @GetMapping("/rest/clients")
    List<Client> getClients() {
        return appService.getClients();
    }

    @GetMapping("/rest/products/search")
    List<Product> findByCategory(@RequestParam Long categoryId) {
        return appService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/rest/clients/{id}/orders")
    List<ClientOrder> findClientOrdersByClientId(@PathVariable Long id) {
        return appService.getClientOrders(id);
    }

    @GetMapping("/rest/clients/{id}/products")
    List<Product> findProductsByClientId(@PathVariable Long id) {
        return appService.getClientProducts(id);
    }

    @GetMapping("/rest/products/popular")
    List<Product> mostPopularProductsWithLimit(@RequestParam Integer limit) {
        return appService.getTopPopularProducts(limit);
    }

}