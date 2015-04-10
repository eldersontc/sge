package com.sge.modulos.inventarios.entidades;

/**
 *
 * @author elderson
 */
public class ProductoAlmacen {
 
    private int idProductoAlmacen;
    private Producto producto;
    private Almacen almacen;
    private double stockFisico;
    private double stockComprometido;
    private double stockSolicitado;
    private double stockDisponible;

    public ProductoAlmacen() {
    }

    public int getIdProductoAlmacen() {
        return idProductoAlmacen;
    }

    public void setIdProductoAlmacen(int idProductoAlmancen) {
        this.idProductoAlmacen = idProductoAlmancen;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
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

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
}
