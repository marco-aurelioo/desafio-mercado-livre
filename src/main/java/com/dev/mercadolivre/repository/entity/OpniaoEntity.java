package com.dev.mercadolivre.repository.entity;

import com.dev.mercadolivre.model.OpniaoModel;
import com.dev.mercadolivre.model.UserModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "opniao" )
public class OpniaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Max(5)
    @Min(1)
    private Integer nota;
    @NotEmpty
    private String titulo;
    @Size(min = 1, max = 500)
    private String descricao;

    @ManyToOne
    private UserEntity usuario;

    @ManyToOne
    private ProdutoEntity produto;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public OpniaoEntity(){}

    public OpniaoEntity(Integer id, Integer nota, String titulo, String descricao, UserEntity usuario, ProdutoEntity produto) {
        this.id = id;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

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

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
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

    public OpniaoModel toModel() {
        return new OpniaoModel(this.id, this.nota, this.titulo, this.descricao, new UserModel(this.usuario.getUsername()), this.produto.getId());
    }
}
