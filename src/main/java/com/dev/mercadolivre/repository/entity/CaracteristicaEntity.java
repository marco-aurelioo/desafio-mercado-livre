package com.dev.mercadolivre.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "caracteristicas")
public class CaracteristicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chave;

    private String valor;

    public CaracteristicaEntity() {

    }

    public CaracteristicaEntity(Long id, String chave, String valor) {
        this.id = id;
        this.chave = chave;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}

