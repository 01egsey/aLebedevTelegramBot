package ru.aLebedev.tgBot.botDB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ru.aLebedev.tgBot.botDB.entity.Category;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);

    List<Category> findByParent_Id(Long id);

    @Query("select c from Category c where c.parent is null")
    List<Category> findRootCategories();

}
