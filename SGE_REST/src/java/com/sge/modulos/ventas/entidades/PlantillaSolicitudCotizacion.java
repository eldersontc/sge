package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PlantillaSolicitudCotizacion {
    
    private int idPlantillaSolicitudCotizacion;
    private String nombre;
    private String lineaProduccion;
    private List<ItemPlantillaSolicitudCotizacion> items;
    private boolean activo;

    public PlantillaSolicitudCotizacion() {
        items = new ArrayList<>();
    }

    public int getIdPlantillaSolicitudCotizacion() {
        return idPlantillaSolicitudCotizacion;
    }

    public void setIdPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion) {
        this.idPlantillaSolicitudCotizacion = idPlantillaSolicitudCotizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLineaProduccion() {
        return lineaProduccion;
    }

    public void setLineaProduccion(String lineaProduccion) {
        this.lineaProduccion = lineaProduccion;
    }

    public List<ItemPlantillaSolicitudCotizacion> getItems() {
        return items;
    }

    public void setItems(List<ItemPlantillaSolicitudCotizacion> items) {
        this.items = items;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
