package com.sge.modulos.finanzas.clases;

/**
 *
 * @author elderson
 */
public class ItemEntradaCaja {
    
    private int idItemEntradaCaja;
    private int idEntradaCaja;
    private String descripcion;
    private double cantidad;
    private double precio;
    private double total;
    
    public ItemEntradaCaja() {
    }

    public int getIdItemEntradaCaja() {
        return idItemEntradaCaja;
    }

    public void setIdItemEntradaCaja(int idItemEntradaCaja) {
        this.idItemEntradaCaja = idItemEntradaCaja;
    }

    public int getIdEntradaCaja() {
        return idEntradaCaja;
    }

    public void setIdEntradaCaja(int idEntradaCaja) {
        this.idEntradaCaja = idEntradaCaja;
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
