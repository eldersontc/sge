package com.sge.modulos.inventarios.entidades;

/**
 *
 * @author elderson
 */
public class ProductoAlmacen {
 
    private int idProductoAlmacen;
    private Producto producto;
    private Almacen almacen;
    private int stockFisico;
    private int stockComprometido;
    private int stockSolicitado;
    private int stockDisponible;

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

    public int getStockFisico() {
        return stockFisico;
    }

    public void setStockFisico(int stockFisico) {
        this.stockFisico = stockFisico;
    }

    public int getStockComprometido() {
        return stockComprometido;
    }

    public void setStockComprometido(int stockComprometido) {
        this.stockComprometido = stockComprometido;
    }

    public int getStockSolicitado() {
        return stockSolicitado;
    }

    public void setStockSolicitado(int stockSolicitado) {
        this.stockSolicitado = stockSolicitado;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
}
