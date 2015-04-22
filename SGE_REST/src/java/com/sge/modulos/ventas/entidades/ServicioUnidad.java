package com.sge.modulos.ventas.entidades;

/**
 *
 * @author elderson
 */
public class ServicioUnidad {
    
    private int idServicioUnidad;
    private int idServicio;
    private int idUnidad;
    private String abreviacionUnidad;
    private int factor;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ServicioUnidad() {
    }

    public int getIdServicioUnidad() {
        return idServicioUnidad;
    }

    public void setIdServicioUnidad(int idServicioUnidad) {
        this.idServicioUnidad = idServicioUnidad;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
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
