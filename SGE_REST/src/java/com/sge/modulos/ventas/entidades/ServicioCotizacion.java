package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ServicioCotizacion {
    
    private int idServicioCotizacion;
    private int idItemCotizacion;
    private int idServicio;
    private String descripcionServicio;
    private int idServicioUnidad;
    private String abreviacionUnidad;
    private boolean precioManual;
    private double precio;
    private double cantidad;
    private double total;
    private List<ServicioUnidad> unidades;
    private ServicioUnidad unidad;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;
    
    public ServicioCotizacion() {
        unidades = new ArrayList<>();
    }

    public int getIdServicioCotizacion() {
        return idServicioCotizacion;
    }

    public void setIdServicioCotizacion(int idServicioCotizacion) {
        this.idServicioCotizacion = idServicioCotizacion;
    }

    public int getIdItemCotizacion() {
        return idItemCotizacion;
    }

    public void setIdItemCotizacion(int idItemCotizacion) {
        this.idItemCotizacion = idItemCotizacion;
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

    public List<ServicioUnidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<ServicioUnidad> unidades) {
        this.unidades = unidades;
    }

    public ServicioUnidad getUnidad() {
        return unidad;
    }

    public void setUnidad(ServicioUnidad unidad) {
        this.unidad = unidad;
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
