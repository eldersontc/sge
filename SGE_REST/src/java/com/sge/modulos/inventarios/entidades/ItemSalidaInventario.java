package com.sge.modulos.inventarios.entidades;

/**
 *
 * @author elderson
 */
public class ItemSalidaInventario {
 
    private int idItemSalidaInventario;
    private int idSalidaInventario;
    private int idProducto;
    private String codigoProducto;
    private String descripcionProducto;
    private int idAlmacen;
    private int idUnidad;
    private int factor;
    private String abreviacionUnidad;
    private double precio;
    private double cantidad;
    private double cantidadMaxima;
    private double tipoCambio;
    private double total;

    public ItemSalidaInventario() {
    }

    public int getIdItemSalidaInventario() {
        return idItemSalidaInventario;
    }

    public void setIdItemSalidaInventario(int idItemSalidaInventario) {
        this.idItemSalidaInventario = idItemSalidaInventario;
    }

    public int getIdSalidaInventario() {
        return idSalidaInventario;
    }

    public void setIdSalidaInventario(int idSalidaInventario) {
        this.idSalidaInventario = idSalidaInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public String getAbreviacionUnidad() {
        return abreviacionUnidad;
    }

    public void setAbreviacionUnidad(String abreviacionUnidad) {
        this.abreviacionUnidad = abreviacionUnidad;
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
    
    public double getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(double cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }
    
    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
