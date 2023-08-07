package com.dev.mercadolivre.repository.entity;

import com.dev.mercadolivre.model.GrupoCaracteristicaModel;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "grupo_caracteristicas")
public class GrupoCaracteristicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String grupoCaracteristicas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grupo_caracteristica_id")
    private List<CaracteristicaEntity> caracteristicas;

    public GrupoCaracteristicaEntity() {

    }

    public GrupoCaracteristicaEntity(Integer id, String grupoCaracteristicas, List<CaracteristicaEntity> caracteristicas) {
        this.id = id;
        this.grupoCaracteristicas = grupoCaracteristicas;
        this.caracteristicas = caracteristicas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrupoCaracteristicas() {
        return grupoCaracteristicas;
    }

    public void setGrupoCaracteristicas(String grupoCaracteristicas) {
        this.grupoCaracteristicas = grupoCaracteristicas;
    }

    public List<CaracteristicaEntity> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaEntity> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public GrupoCaracteristicaModel toModel() {
        GrupoCaracteristicaModel grupo = new GrupoCaracteristicaModel();
        grupo.setGrupoCaracteristicas(this.grupoCaracteristicas);
        HashMap<String,String> caracteristicas = new HashMap<>();
        for(CaracteristicaEntity caracteristica : this.caracteristicas) {
            caracteristicas.put(caracteristica.getChave(), caracteristica.getValor());
        }
        grupo.setCaracteristicas(caracteristicas);
        return grupo;
    }
}
