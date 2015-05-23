package com.sge.modulos.administracion.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class Reporte {
    
    private int idReporte;
    private String nombre;
    private int idEntidad;
    private String nombreEntidad;
    private String ubicacion;
    private boolean activo;
    private List<ItemReporte> items;

    public Reporte() {
        items = new ArrayList<>();
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

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
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
