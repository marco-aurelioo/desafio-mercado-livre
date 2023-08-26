package com.dev.mercadolivre.controllers.model;

import com.dev.mercadolivre.model.OpniaoModel;
import com.dev.mercadolivre.model.UserModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OpniaoRequest {

    @Max(5)
    @Min(1)
    private Integer nota;
    @NotEmpty
    private String titulo;
    @Size(min = 1, max = 500)
    private String descricao;

    public OpniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
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

    public OpniaoModel toModel(String username, Integer produtoId) {
        return new OpniaoModel(null, this.nota, this.titulo, this.descricao,
                new UserModel( username ), produtoId);

    }
}
