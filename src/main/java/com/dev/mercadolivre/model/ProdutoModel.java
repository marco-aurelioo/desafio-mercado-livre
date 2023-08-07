package com.dev.mercadolivre.model;

import com.dev.mercadolivre.repository.CategoryRepository;
import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.ProdutoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoModel {

    private Integer id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidadeDisponivel;
    private List<GrupoCaracteristicaModel> caracteristicas;
    private CategoryModel categoria;
    private String descricao;
    private LocalDateTime dataCadastro;
    private UserModel user;

    public ProdutoModel(String userName, String nome, BigDecimal valor, Integer quantidadeDisponivel, List<GrupoCaracteristicaModel> caracteristicas, CategoryModel categoria, String descricao, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.user = new UserModel(userName);
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

    public List<GrupoCaracteristicaModel> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<GrupoCaracteristicaModel> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public CategoryModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoryModel categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public ProdutoEntity toEntity( UserRepository  userRepository, CategoryRepository categoryRepository) {
        return new ProdutoEntity(
                null,
                this.nome,
                this.valor,
                this.quantidadeDisponivel,
                this.caracteristicas.stream().map(
                        GrupoCaracteristicaModel::toEntity
                ).collect(Collectors.toList()),
                categoryRepository.findById(this.categoria.getId()).orElse(null),
                userRepository.findByUsername(this.user.getUsername()),
                this.descricao
        );
    }
}
