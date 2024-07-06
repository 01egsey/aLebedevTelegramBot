package ru.aLebedev.tgBot.botDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.aLebedev.tgBot.botDB.entity.Client;

@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")

public interface ClientRepository extends JpaRepository<Client, Long> {

}
