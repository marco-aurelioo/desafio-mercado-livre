package com.dev.mercadolivre.model;

import java.util.HashMap;

public class GrupoCaracteristicaModel {

    private String grupoCaracteristicas;
    private HashMap<String, String> caracteristicas;

    public GrupoCaracteristicaModel(String grupoCaracteristicas, HashMap<String, String> caracteristicas) {
        this.grupoCaracteristicas = grupoCaracteristicas;
        this.caracteristicas = caracteristicas;
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
}
