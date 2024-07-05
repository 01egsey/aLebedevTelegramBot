package ru.aLebedev.tgBot.botDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.aLebedev.tgBot.botDB.entity.Product;


@RepositoryRestResource(collectionResourceRel =
"products", path = "products")

public interface ProductRepository extends JpaRepository<Product, Long>{


}
