package com.sge.modulos.inventarios.entidades;

/**
 *
 * @author elderson
 */
public class ProductoAlmacen {
 
    private int idProductoAlmacen;
    private int idProducto;
    private int idAlmacen;
    private String descripcionAlmacen;
    private double stockFisico;
    private double stockComprometido;
    private double stockSolicitado;
    private double stockDisponible;

    public ProductoAlmacen() {
    }

    public int getIdProductoAlmacen() {
        return idProductoAlmacen;
    }

    public void setIdProductoAlmacen(int idProductoAlmacen) {
        this.idProductoAlmacen = idProductoAlmacen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDescripcionAlmacen() {
        return descripcionAlmacen;
    }

    public void setDescripcionAlmacen(String descripcionAlmacen) {
        this.descripcionAlmacen = descripcionAlmacen;
    }

    public double getStockFisico() {
        return stockFisico;
    }

    public void setStockFisico(double stockFisico) {
        this.stockFisico = stockFisico;
    }

    public double getStockComprometido() {
        return stockComprometido;
    }

    public void setStockComprometido(double stockComprometido) {
        this.stockComprometido = stockComprometido;
    }

    public double getStockSolicitado() {
        return stockSolicitado;
    }

    public void setStockSolicitado(double stockSolicitado) {
        this.stockSolicitado = stockSolicitado;
    }

    public double getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(double stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
}
