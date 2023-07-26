package com.dev.mercadolivre.repository.entity;

import com.dev.mercadolivre.model.CategoryModel;
import com.dev.mercadolivre.repository.CategoryRepository;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_ID")
    private CategoryEntity parent;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public CategoryEntity(){}

    public CategoryEntity(CategoryModel categoryModel, CategoryRepository categoryRepository){
        this.id = categoryModel.getId();
        this.name = categoryModel.getName();
        if(categoryModel.getParentCategory().getId() != null) {
            Optional<CategoryEntity> parent = categoryRepository.findById(categoryModel.getParentCategory().getId());
            this.parent = parent.orElse(null);
        }
    }

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public void setParent(CategoryEntity parent) {
        this.parent = parent;
    }

    @PreUpdate
    private void postUpdateFunction(){
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    private void prePersistFunction(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public CategoryModel toModel( CategoryRepository categoryRepository) {
        CategoryModel category = new CategoryModel(this.id, this.name);
        if(this.parent != null){
            category.setParentCategory(this.parent.toModel(categoryRepository));
        }
        return category;
    }
}
