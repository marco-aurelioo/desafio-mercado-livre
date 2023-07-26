package com.dev.mercadolivre.repository;

import com.dev.mercadolivre.repository.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface CategoryRepository extends CrudRepository< CategoryEntity, Integer> {

    Optional<CategoryEntity> findByName(String name);

}
