package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.controllers.model.CategoryRequest;
import com.dev.mercadolivre.model.CategoryModel;
import com.dev.mercadolivre.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest.toModel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategory(
            @PathVariable(name = "id") Integer id
    ) {
        return ResponseEntity.ok(categoryService.findCategory(id));
    }


}
