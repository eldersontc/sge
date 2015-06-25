package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class GrupoSolicitudCotizacion {
    
    private int idGrupoSolicitudCotizacion;
    private int idSolicitudCotizacion;
    private String nombre;
    private String lineaProduccion;
    private int cantidad;
    private List<ItemSolicitudCotizacion> items;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public GrupoSolicitudCotizacion() {
        items = new ArrayList<>();
    }
    
    public int getIdGrupoSolicitudCotizacion() {
        return idGrupoSolicitudCotizacion;
    }

    public void setIdGrupoSolicitudCotizacion(int idGrupoSolicitudCotizacion) {
        this.idGrupoSolicitudCotizacion = idGrupoSolicitudCotizacion;
    }

    public int getIdSolicitudCotizacion() {
        return idSolicitudCotizacion;
    }

    public void setIdSolicitudCotizacion(int idSolicitudCotizacion) {
        this.idSolicitudCotizacion = idSolicitudCotizacion;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<ItemSolicitudCotizacion> getItems() {
        return items;
    }

    public void setItems(List<ItemSolicitudCotizacion> items) {
        this.items = items;
    }
    
    @Override
    public String toString(){
        return nombre;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
}
