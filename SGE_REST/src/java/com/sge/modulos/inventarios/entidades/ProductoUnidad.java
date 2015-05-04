package com.sge.modulos.inventarios.entidades;

/**
 *
 * @author elderson
 */
public class ProductoUnidad {
    
    private int idProductoUnidad;
    private int idProducto;
    private int idUnidad;
    private String abreviacionUnidad;
    private int factor;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ProductoUnidad() {
    }

    public int getIdProductoUnidad() {
        return idProductoUnidad;
    }

    public void setIdProductoUnidad(int idProductoUnidad) {
        this.idProductoUnidad = idProductoUnidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getAbreviacionUnidad() {
        return abreviacionUnidad;
    }

    public void setAbreviacionUnidad(String abreviacionUnidad) {
        this.abreviacionUnidad = abreviacionUnidad;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
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
