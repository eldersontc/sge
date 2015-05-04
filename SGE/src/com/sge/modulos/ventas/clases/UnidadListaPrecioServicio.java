package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class UnidadListaPrecioServicio {
    
    private int idUnidadListaPrecioServicio;
    private int idItemListaPrecioServicio;
    private int idServicioUnidad;
    private String abreviacionUnidad;
    private int factor;

    public UnidadListaPrecioServicio() {
    }

    public int getIdUnidadListaPrecioServicio() {
        return idUnidadListaPrecioServicio;
    }

    public void setIdUnidadListaPrecioServicio(int idUnidadListaPrecioServicio) {
        this.idUnidadListaPrecioServicio = idUnidadListaPrecioServicio;
    }

    public int getIdItemListaPrecioServicio() {
        return idItemListaPrecioServicio;
    }

    public void setIdItemListaPrecioServicio(int idItemListaPrecioServicio) {
        this.idItemListaPrecioServicio = idItemListaPrecioServicio;
    }

    public int getIdServicioUnidad() {
        return idServicioUnidad;
    }

    public void setIdServicioUnidad(int idServicioUnidad) {
        this.idServicioUnidad = idServicioUnidad;
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
