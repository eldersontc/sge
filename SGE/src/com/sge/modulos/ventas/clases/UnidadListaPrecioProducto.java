package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class UnidadListaPrecioProducto {
    
    private int idUnidadListaPrecioProducto;
    private int idItemListaPrecioProducto;
    private int idProductoUnidad;
    private String abreviacionUnidad;
    private int factor;

    public UnidadListaPrecioProducto() {
    }

    public int getIdUnidadListaPrecioProducto() {
        return idUnidadListaPrecioProducto;
    }

    public void setIdUnidadListaPrecioProducto(int idUnidadListaPrecioProducto) {
        this.idUnidadListaPrecioProducto = idUnidadListaPrecioProducto;
    }

    public int getIdItemListaPrecioProducto() {
        return idItemListaPrecioProducto;
    }

    public void setIdItemListaPrecioProducto(int idItemListaPrecioProducto) {
        this.idItemListaPrecioProducto = idItemListaPrecioProducto;
    }

    public int getIdProductoUnidad() {
        return idProductoUnidad;
    }

    public void setIdProductoUnidad(int idProductoUnidad) {
        this.idProductoUnidad = idProductoUnidad;
    }

    public String getAbreviacionUnidad() {
        return abreviacionUnidad;
    }

    public void setAbreviacionUnidad(String abreviacionUnidad) {
        this.abreviacionUnidad = abreviacionUnidad;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
