package com.sge.modulos.inventarios.clases;

import java.util.ArrayList;
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
    private double alto;
    private double largo;
    private int idUnidadBase;
    private String abreviacionUnidadBase;
    private int factorUnidadBase;
    private boolean activo;
    private List<ProductoUnidad> unidades;
    private List<ProductoAlmacen> almacenes;

    public Producto() {
        unidades = new ArrayList<>();
        almacenes = new ArrayList<>();
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

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
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
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<ProductoUnidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<ProductoUnidad> unidades) {
        this.unidades = unidades;
    }

    public List<ProductoAlmacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<ProductoAlmacen> almacenes) {
        this.almacenes = almacenes;
    }
}
