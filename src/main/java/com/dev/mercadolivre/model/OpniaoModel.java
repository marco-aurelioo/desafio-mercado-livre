package com.dev.mercadolivre.model;


import com.dev.mercadolivre.repository.ProdutoRepository;
import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.OpniaoEntity;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OpniaoModel {

    private Integer id;
    @Size(min = 1, max = 5)
    private Integer nota;
    @NotEmpty
    private String titulo;
    @Max(500)
    private String descricao;

    private Integer produtoId;

    private UserModel user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public OpniaoModel(Integer id, Integer nota, String titulo, String descricao, UserModel user, Integer produtoId) {
        this.id = id;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.user = user;
        this.produtoId = produtoId;
    }

    public OpniaoEntity toEntity(UserRepository userRepository, ProdutoEntity produto) {
        return new OpniaoEntity(
            this.id,
            this.nota,
            this.titulo,
            this.descricao,
            userRepository.findByUsername(this.user.getUsername()),
            produto
        );
    }
}
