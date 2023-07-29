package com.dev.mercadolivre.service;

import com.dev.mercadolivre.model.CategoryModel;
import com.dev.mercadolivre.repository.CategoryRepository;
import com.dev.mercadolivre.repository.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategory(CategoryModel categoryModel){
        canInserirCategoria(categoryModel);
        return categoryRepository.save( new CategoryEntity(categoryModel, categoryRepository)).toModel(categoryRepository);
    }

    private void canInserirCategoria(CategoryModel categoryModel) {
        categoryRepository.findByName(categoryModel.getName()).ifPresent(c -> {
            throw new IllegalArgumentException("Categoria já cadastrada");
        });
    }

}
