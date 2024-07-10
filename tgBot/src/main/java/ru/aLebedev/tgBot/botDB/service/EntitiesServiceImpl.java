package ru.aLebedev.tgBot.botDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.aLebedev.tgBot.botDB.entity.Category;
import ru.aLebedev.tgBot.botDB.entity.Client;
import ru.aLebedev.tgBot.botDB.entity.ClientOrder;
import ru.aLebedev.tgBot.botDB.entity.Product;
import ru.aLebedev.tgBot.botDB.repository.CategoryRepository;
import ru.aLebedev.tgBot.botDB.repository.ClientOrderRepository;
import ru.aLebedev.tgBot.botDB.repository.ClientRepository;
import ru.aLebedev.tgBot.botDB.repository.OrderProductRepository;
import ru.aLebedev.tgBot.botDB.repository.ProductRepository;

@Service
@Transactional
public class EntitiesServiceImpl implements EntitiesService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        Category category = categoryRepository.findCategoryById(id);
        return productRepository.findByCategory(category);
    }

    @Override
    public List<ClientOrder> getClientOrders(Long id) {
        return clientOrderRepository.findClientOrdersByClientId(id);
    }

    @Override
    public List<Product> getClientProducts(Long id) {
        return orderProductRepository.findProductsByClientId(id);
    }

    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        Pageable pageable = PageRequest.of(0, limit); // Создание объекта Pageable для задания лимита
        List<Product> popularProducts = orderProductRepository.mostPopularProductsWithLimit(pageable);
        return popularProducts;
    }

}