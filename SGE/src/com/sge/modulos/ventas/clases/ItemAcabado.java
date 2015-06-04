package com.sge.modulos.ventas.clases;

import java.util.List;

/**
 *
 * @author elderson
 */
public class ItemAcabado {
    
    private int idItemAcabado;
    private int idItemMaterial;
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

    public ItemAcabado() {
    }
    
    public int getIdItemAcabado() {
        return idItemAcabado;
    }

    public void setIdItemAcabado(int idItemAcabado) {
        this.idItemAcabado = idItemAcabado;
    }

    public int getIdItemMaterial() {
        return idItemMaterial;
    }

    public void setIdItemMaterial(int idItemMaterial) {
        this.idItemMaterial = idItemMaterial;
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
}
