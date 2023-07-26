package com.dev.mercadolivre.model;

public class CategoryModel {

    private CategoryModel parentCategory;

    private String name;

    private Integer id;

    public CategoryModel(Integer parentCategoryId, String name, Integer id) {
        CategoryModel parethCategoryInput = new CategoryModel(parentCategoryId, null);
        this.parentCategory = parethCategoryInput;
        this.name = name;
        this.id = id;
    }

    public CategoryModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryModel parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
