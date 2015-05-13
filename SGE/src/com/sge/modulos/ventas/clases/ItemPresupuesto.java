package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class ItemPresupuesto {
    
    private int idItemPresupuesto;
    private int idPresupuesto;
    private int idCotizacion;
    private String numeroCotizacion;
    private String descripcionCotizacion;
    private double totalCotizacion;
    private double recargo;
    private double total;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ItemPresupuesto() {
    }

    public int getIdItemPresupuesto() {
        return idItemPresupuesto;
    }

    public void setIdItemPresupuesto(int idItemPresupuesto) {
        this.idItemPresupuesto = idItemPresupuesto;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public String getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(String numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getDescripcionCotizacion() {
        return descripcionCotizacion;
    }

    public void setDescripcionCotizacion(String descripcionCotizacion) {
        this.descripcionCotizacion = descripcionCotizacion;
    }

    public double getTotalCotizacion() {
        return totalCotizacion;
    }

    public void setTotalCotizacion(double totalCotizacion) {
        this.totalCotizacion = totalCotizacion;
    }

    public double getRecargo() {
        return recargo;
    }

    public void setRecargo(double recargo) {
        this.recargo = recargo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
