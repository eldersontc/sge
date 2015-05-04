package com.sge.modulos.ventas.entidades;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioServicio {
    
    private int idEscalaListaPrecioServicio;
    private int idUnidadListaPrecioServicio;
    private int desde;
    private int hasta;
    private double precio;

    public EscalaListaPrecioServicio() {
    }

    public int getIdEscalaListaPrecioServicio() {
        return idEscalaListaPrecioServicio;
    }

    public void setIdEscalaListaPrecioServicio(int idEscalaListaPrecioServicio) {
        this.idEscalaListaPrecioServicio = idEscalaListaPrecioServicio;
    }

    public int getIdUnidadListaPrecioServicio() {
        return idUnidadListaPrecioServicio;
    }

    public void setIdUnidadListaPrecioServicio(int idUnidadListaPrecioServicio) {
        this.idUnidadListaPrecioServicio = idUnidadListaPrecioServicio;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
