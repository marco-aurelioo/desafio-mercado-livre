package com.dev.mercadolivre.controllers;

import com.dev.mercadolivre.model.CategoryModel;

public class CategoryRequest {

    private String name;
    private Integer id;
    private Integer parentId;


    public CategoryRequest(String name, Integer id, Integer parentId) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public CategoryModel toModel() {
        return new CategoryModel(parentId,name, id );
    }
}
