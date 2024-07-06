package ru.aLebedev.tgBot.botDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.aLebedev.tgBot.botDB.entity.ClientOrder;

@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

}
