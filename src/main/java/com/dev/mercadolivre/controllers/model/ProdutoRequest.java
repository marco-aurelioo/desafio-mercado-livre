package com.dev.mercadolivre.controllers.model;

import com.dev.mercadolivre.model.CategoryModel;
import com.dev.mercadolivre.model.ProdutoModel;
import com.dev.mercadolivre.model.UserModel;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoRequest {


    @NotBlank
    private String nome;
    @Min(0)
    private BigDecimal valor;
    @Min(0)
    private Integer quantidadeDisponivel;
    @NotEmpty
    private List<GrupoCaracteristica> caracteristicas;
    @Length(min=10, max=1000)
    private String descricao;
    @NotNull
    private Integer categoriaId;

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
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

    public List<GrupoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<GrupoCaracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ProdutoModel toModel(UserDetails user) {
        return new ProdutoModel(
                user.getUsername(),
                this.nome,
                this.valor,
                this.quantidadeDisponivel,
                this.caracteristicas.stream().map(GrupoCaracteristica::toModel).toList(),
                new CategoryModel(this.categoriaId),
                descricao,
                null);
    }

    public ProdutoModel toModel(UserDetails user, LocalDateTime dataCadastro) {
        ProdutoModel model = this.toModel(user);
        model.setDataCadastro(dataCadastro);
        return model;
    }

}
