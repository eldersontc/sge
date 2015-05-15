package com.sge.modulos.produccion.entidades;

import java.util.Date;

/**
 *
 * @author elderson
 */
public class ItemOrdenProduccion {
    
    private int idItemOrdenProduccion;
    private int idOrdenProduccion;
    private int idOrdenTrabajo;
    private String numeroOrdenTrabajo;
    private String descripcionOrdenTrabajo;
    private int cantidadOrdenTrabajo;
    private Date fechaOrdenTrabajo;
    private boolean agregar;
    private boolean eliminar;

    public ItemOrdenProduccion() {
    }

    public int getIdItemOrdenProduccion() {
        return idItemOrdenProduccion;
    }

    public void setIdItemOrdenProduccion(int idItemOrdenProduccion) {
        this.idItemOrdenProduccion = idItemOrdenProduccion;
    }

    public int getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(int idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public String getNumeroOrdenTrabajo() {
        return numeroOrdenTrabajo;
    }

    public void setNumeroOrdenTrabajo(String numeroOrdenTrabajo) {
        this.numeroOrdenTrabajo = numeroOrdenTrabajo;
    }

    public String getDescripcionOrdenTrabajo() {
        return descripcionOrdenTrabajo;
    }

    public void setDescripcionOrdenTrabajo(String descripcionOrdenTrabajo) {
        this.descripcionOrdenTrabajo = descripcionOrdenTrabajo;
    }
    
    public int getCantidadOrdenTrabajo() {
        return cantidadOrdenTrabajo;
    }

    public void setCantidadOrdenTrabajo(int cantidadOrdenTrabajo) {
        this.cantidadOrdenTrabajo = cantidadOrdenTrabajo;
    }

    public Date getFechaOrdenTrabajo() {
        return fechaOrdenTrabajo;
    }

    public void setFechaOrdenTrabajo(Date fechaOrdenTrabajo) {
        this.fechaOrdenTrabajo = fechaOrdenTrabajo;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
}
