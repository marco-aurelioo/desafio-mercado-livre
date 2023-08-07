package com.dev.mercadolivre.repository.entity;

import com.dev.mercadolivre.model.ProdutoModel;
import com.dev.mercadolivre.repository.CategoryRepository;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidadeDisponivel;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<GrupoCaracteristicaEntity> caracteristicas;

    @ManyToOne
    private CategoryEntity categoria;

    @ManyToOne
    private UserEntity usuario;

    private String descricao;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public ProdutoEntity() {
    }

    public ProdutoEntity(Integer id, String nome, BigDecimal valor, Integer quantidadeDisponivel, List<GrupoCaracteristicaEntity> caracteristicas, CategoryEntity categoria, UserEntity user, String descricao) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.usuario = user;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public List<GrupoCaracteristicaEntity> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<GrupoCaracteristicaEntity> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public CategoryEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryEntity categoria) {
        this.categoria = categoria;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUssuario(UserEntity user) {
        this.usuario = user;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    public ProdutoModel toModel(CategoryRepository categoryRepository) {
        return new ProdutoModel(
                this.usuario.getUsername(),
                this.nome,
                this.valor,
                this.quantidadeDisponivel,
                this.caracteristicas.stream()
                        .map(entity -> entity.toModel())
                        .collect(Collectors.toList()),
                this.categoria.toModel(categoryRepository),
                this.descricao,
                this.createdAt);
    }

}
