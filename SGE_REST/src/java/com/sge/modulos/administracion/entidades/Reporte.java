package com.sge.modulos.administracion.entidades;

import java.util.List;

/**
 *
 * @author elderson
 */
public class Reporte {
    
    private int idReporte;
    private String nombre;
    private String entidad;
    private String ubicacion;
    private boolean activo;
    private List<ItemReporte> items;

    public Reporte() {
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<ItemReporte> getItems() {
        return items;
    }

    public void setItems(List<ItemReporte> items) {
        this.items = items;
    }
}
