package com.sge.modulos.ventas.entidades;

import java.util.List;

/**
 *
 * @author elderson
 */
public class UnidadListaPrecioProducto {
    
    private int idUnidadListaPrecioProducto;
    private int idItemListaPrecioProducto;
    private int idProductoUnidad;
    private int abreviacionUnidad;
    private int factor;
    private List<EscalaListaPrecioProducto> escalas;

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

    public int getAbreviacionUnidad() {
        return abreviacionUnidad;
    }

    public void setAbreviacionUnidad(int abreviacionUnidad) {
        this.abreviacionUnidad = abreviacionUnidad;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public List<EscalaListaPrecioProducto> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<EscalaListaPrecioProducto> escalas) {
        this.escalas = escalas;
    }
}
