package com.sge.modulos.finanzas.clases;

/**
 *
 * @author elderson
 */
public class ItemSalidaCaja {
    
    private int idItemSalidaCaja;
    private int idSalidaCaja;
    private String descripcion;
    private double cantidad;
    private double precio;
    private double total;

    public ItemSalidaCaja() {
    }

    public int getIdItemSalidaCaja() {
        return idItemSalidaCaja;
    }

    public void setIdItemSalidaCaja(int idItemSalidaCaja) {
        this.idItemSalidaCaja = idItemSalidaCaja;
    }

    public int getIdSalidaCaja() {
        return idSalidaCaja;
    }

    public void setIdSalidaCaja(int idSalidaCaja) {
        this.idSalidaCaja = idSalidaCaja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
