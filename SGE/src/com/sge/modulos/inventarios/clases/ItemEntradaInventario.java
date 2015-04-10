package com.sge.modulos.inventarios.clases;

/**
 *
 * @author elderson
 */
public class ItemEntradaInventario {
    
    private int idItemEntradaInventario;
    private int idEntradaInventario;
    private int idProducto;
    private String codigoProducto;
    private String descripcionProducto;
    private int idAlmacen;
    private int idUnidad;
    private int factor;
    private String abreviacionUnidad;
    private double precio;
    private double cantidad;
    private double tipoCambio;
    private double total;

    public ItemEntradaInventario() {
    }

    public int getIdItemEntradaInventario() {
        return idItemEntradaInventario;
    }

    public void setIdItemEntradaInventario(int idItemEntradaInventario) {
        this.idItemEntradaInventario = idItemEntradaInventario;
    }

    public int getIdEntradaInventario() {
        return idEntradaInventario;
    }

    public void setIdEntradaInventario(int idEntradaInventario) {
        this.idEntradaInventario = idEntradaInventario;
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
