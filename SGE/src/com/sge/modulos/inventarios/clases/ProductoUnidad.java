package com.sge.modulos.inventarios.clases;

/**
 *
 * @author elderson
 */
public class ProductoUnidad {
    
    private int idProductoUnidad;
    private Producto producto;
    private Unidad unidad;
    private int factor;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ProductoUnidad() {
    }

    public ProductoUnidad(int idProductoUnidad, boolean eliminar) {
        this.idProductoUnidad = idProductoUnidad;
        this.eliminar = eliminar;
    }

    public int getIdProductoUnidad() {
        return idProductoUnidad;
    }

    public void setIdProductoUnidad(int idProductoUnidad) {
        this.idProductoUnidad = idProductoUnidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
}
