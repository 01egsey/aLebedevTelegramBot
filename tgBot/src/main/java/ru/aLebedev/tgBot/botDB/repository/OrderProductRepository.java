package ru.aLebedev.tgBot.botDB.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.aLebedev.tgBot.botDB.entity.OrderProduct;
import ru.aLebedev.tgBot.botDB.entity.Product;

@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query("select op.product from OrderProduct op where op.clientOrder.client.id = :id")
    // Вывести все продукты из продуктовых заказов, где idклиента в
    // клиенте в заказе клиента в заказе равно нужному нам id клиента
    List<Product> findProductsByClientId(Long id);

    @Query("select op.product from OrderProduct op group by op.product order by sum(op.countProduct) desc")
    List<Product> mostPopularProductsWithLimit(Pageable pageable);

}
