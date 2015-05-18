package com.sge.modulos.facturacion.entidades;

/**
 *
 * @author elderson
 */
public class ItemFactura {
    
    private int idItemFactura;
    private int idFactura;
    private int idOrdenTrabajo;
    private String numeroOrdenTrabajo;
    private String descripcionOrdenTrabajo;
    private double cantidad;
    private double precio;
    private double total;

    public ItemFactura() {
    }

    public int getIdItemFactura() {
        return idItemFactura;
    }

    public void setIdItemFactura(int idItemFactura) {
        this.idItemFactura = idItemFactura;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
