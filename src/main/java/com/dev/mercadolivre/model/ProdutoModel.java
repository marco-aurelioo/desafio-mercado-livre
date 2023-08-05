package com.dev.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoModel {

    private String nome;
    private BigDecimal valor;
    private Integer quantidadeDisponivel;
    private List<GrupoCaracteristicaModel> caracteristicas;
    private CategoryModel categoria;
    private String descricao;
    private LocalDateTime dataCadastro;

    public ProdutoModel(String nome, BigDecimal valor, Integer quantidadeDisponivel, List<GrupoCaracteristicaModel> caracteristicas, CategoryModel categoria, String descricao, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
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
}
