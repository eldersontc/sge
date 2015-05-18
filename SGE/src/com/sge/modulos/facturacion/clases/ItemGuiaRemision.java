package com.sge.modulos.facturacion.clases;

/**
 *
 * @author elderson
 */
public class ItemGuiaRemision {
    
    private int idItemGuiaRemision;
    private int idGuiaRemision;
    private int idOrdenTrabajo;
    private String numeroOrdenTrabajo;
    private String descripcionOrdenTrabajo;
    private int cantidad;
    private boolean agregar;
    private boolean eliminar;

    public ItemGuiaRemision() {
    }

    public int getIdItemGuiaRemision() {
        return idItemGuiaRemision;
    }

    public void setIdItemGuiaRemision(int idItemGuiaRemision) {
        this.idItemGuiaRemision = idItemGuiaRemision;
    }

    public int getIdGuiaRemision() {
        return idGuiaRemision;
    }

    public void setIdGuiaRemision(int idGuiaRemision) {
        this.idGuiaRemision = idGuiaRemision;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
