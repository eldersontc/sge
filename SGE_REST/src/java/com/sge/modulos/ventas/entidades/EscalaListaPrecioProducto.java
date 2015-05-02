package com.sge.modulos.ventas.entidades;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioProducto {
 
    private int idEscalaListaPrecioProducto;
    private int idUnidadListaPrecioProducto;
    private int desde;
    private int hasta;
    private double precio;

    public EscalaListaPrecioProducto() {
    }

    public int getIdEscalaListaPrecioProducto() {
        return idEscalaListaPrecioProducto;
    }

    public void setIdEscalaListaPrecioProducto(int idEscalaListaPrecioProducto) {
        this.idEscalaListaPrecioProducto = idEscalaListaPrecioProducto;
    }

    public int getIdUnidadListaPrecioProducto() {
        return idUnidadListaPrecioProducto;
    }

    public void setIdUnidadListaPrecioProducto(int idUnidadListaPrecioProducto) {
        this.idUnidadListaPrecioProducto = idUnidadListaPrecioProducto;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
