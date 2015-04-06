package com.sge.modulos.inventarios.clases;

import java.util.List;

/**
 *
 * @author elderson
 */
public class Producto {
 
    private int idProducto;
    private String codigo;
    private String descripcion;
    private boolean inventarios;
    private boolean compras;
    private boolean ventas;
    private double costoUltimaCompra;
    private double costoPromedio;
    private double costoReferencia;
    private boolean activo;
    private List<Object[]> productoUnidades;

    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isInventarios() {
        return inventarios;
    }

    public void setInventarios(boolean inventarios) {
        this.inventarios = inventarios;
    }

    public boolean isCompras() {
        return compras;
    }

    public void setCompras(boolean compras) {
        this.compras = compras;
    }

    public boolean isVentas() {
        return ventas;
    }

    public void setVentas(boolean ventas) {
        this.ventas = ventas;
    }

    public List<Object[]> getProductoUnidades() {
        return productoUnidades;
    }

    public void setProductoUnidades(List<Object[]> productoUnidades) {
        this.productoUnidades = productoUnidades;
    }
}
