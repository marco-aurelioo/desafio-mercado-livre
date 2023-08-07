package com.dev.mercadolivre.controllers.model;

import com.dev.mercadolivre.model.GrupoCaracteristicaModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

public class GrupoCaracteristica {

    @NotBlank
    private String grupoCaracteristicas;
    @NotEmpty
    private HashMap<String,String> caracteristicas;

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

    public GrupoCaracteristica(String grupoCaracteristicas, HashMap<String, String> caracteristicas) {
        this.grupoCaracteristicas = grupoCaracteristicas;
        this.caracteristicas = caracteristicas;
    }

    public GrupoCaracteristicaModel toModel() {
        return new GrupoCaracteristicaModel(grupoCaracteristicas, caracteristicas);
    }
}
