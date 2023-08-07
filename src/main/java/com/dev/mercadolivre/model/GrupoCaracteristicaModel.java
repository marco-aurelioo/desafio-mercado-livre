package com.dev.mercadolivre.model;

import com.dev.mercadolivre.repository.entity.CaracteristicaEntity;
import com.dev.mercadolivre.repository.entity.GrupoCaracteristicaEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrupoCaracteristicaModel {

    private String grupoCaracteristicas;
    private HashMap<String, String> caracteristicas;

    public GrupoCaracteristicaModel(String grupoCaracteristicas, HashMap<String, String> caracteristicas) {
        this.grupoCaracteristicas = grupoCaracteristicas;
        this.caracteristicas = caracteristicas;
    }

    public GrupoCaracteristicaModel() {

    }

    public String getGrupoCaracteristicas() {
        return grupoCaracteristicas;
    }

    public void setGrupoCaracteristicas(String grupoCaracteristicas) {
        this.grupoCaracteristicas = grupoCaracteristicas;
    }

    public HashMap<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(HashMap<String, String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public GrupoCaracteristicaEntity toEntity() {
        List<CaracteristicaEntity> caracteristicasList = new ArrayList<>();
        for(String key : this.caracteristicas.keySet()) {
            caracteristicasList.add(
            new CaracteristicaEntity(
                    null,
                    key,
                    this.caracteristicas.get(key)
            ));
        }

        return new GrupoCaracteristicaEntity(
                null,
                this.grupoCaracteristicas,
                caracteristicasList
        );

    }
}
