package com.sge.modulos.inventarios.entidades;

/**
 *
 * @author elderson
 */
public class SeleccionProducto {
    
    private int idProducto;
    private String codigoProducto;
    private String descripcionProducto;
    private int idUnidadBase;
    private String abreviacionUnidadBase;
    private int factorUnidadBase;
    private int stock;
    private double costoUltimaCompra;
    private double costoPromedio;
    private double costoReferencia;
    private double precio;

    public SeleccionProducto() {
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

    public int getIdUnidadBase() {
        return idUnidadBase;
    }

    public void setIdUnidadBase(int idUnidadBase) {
        this.idUnidadBase = idUnidadBase;
    }

    public String getAbreviacionUnidadBase() {
        return abreviacionUnidadBase;
    }

    public void setAbreviacionUnidadBase(String abreviacionUnidadBase) {
        this.abreviacionUnidadBase = abreviacionUnidadBase;
    }

    public int getFactorUnidadBase() {
        return factorUnidadBase;
    }

    public void setFactorUnidadBase(int factorUnidadBase) {
        this.factorUnidadBase = factorUnidadBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getCostoUltimaCompra() {
        return costoUltimaCompra;
    }

    public void setCostoUltimaCompra(double costoUltimaCompra) {
        this.costoUltimaCompra = costoUltimaCompra;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public double getCostoReferencia() {
        return costoReferencia;
    }

    public void setCostoReferencia(double costoReferencia) {
        this.costoReferencia = costoReferencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
