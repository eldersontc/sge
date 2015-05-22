package com.sge.modulos.produccion.entidades;

/**
 *
 * @author elderson
 */
public class ServicioOrdenTrabajo {
    
    private int idServicioOrdenTrabajo;
    private int idItemOrdenTrabajo;
    private int idServicio;
    private String descripcionServicio;
    private int idServicioUnidad;
    private String abreviacionUnidad;
    private boolean precioManual;
    private double precio;
    private double cantidad;
    private double total;

    public ServicioOrdenTrabajo() {
    }

    public int getIdServicioOrdenTrabajo() {
        return idServicioOrdenTrabajo;
    }

    public void setIdServicioOrdenTrabajo(int idServicioOrdenTrabajo) {
        this.idServicioOrdenTrabajo = idServicioOrdenTrabajo;
    }

    public int getIdItemOrdenTrabajo() {
        return idItemOrdenTrabajo;
    }

    public void setIdItemOrdenTrabajo(int idItemOrdenTrabajo) {
        this.idItemOrdenTrabajo = idItemOrdenTrabajo;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public int getIdServicioUnidad() {
        return idServicioUnidad;
    }

    public void setIdServicioUnidad(int idServicioUnidad) {
        this.idServicioUnidad = idServicioUnidad;
    }

    public String getAbreviacionUnidad() {
        return abreviacionUnidad;
    }

    public void setAbreviacionUnidad(String abreviacionUnidad) {
        this.abreviacionUnidad = abreviacionUnidad;
    }

    public boolean isPrecioManual() {
        return precioManual;
    }

    public void setPrecioManual(boolean precioManual) {
        this.precioManual = precioManual;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
