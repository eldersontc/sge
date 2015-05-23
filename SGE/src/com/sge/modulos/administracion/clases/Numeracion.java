package com.sge.modulos.administracion.clases;

/**
 *
 * @author elderson
 */
public class Numeracion {
    
    private int idNumeracion;
    private String descripcion;
    private int idEntidad;
    private String nombreEntidad;
    private boolean manual;
    private String serie;
    private int numeroActual;
    private int longitudNumero;
    private boolean tieneImpuesto;
    private double porcentajeImpuesto;
    private boolean activo;

    public Numeracion() {
    }

    public int getIdNumeracion() {
        return idNumeracion;
    }

    public void setIdNumeracion(int idNumeracion) {
        this.idNumeracion = idNumeracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getNumeroActual() {
        return numeroActual;
    }

    public void setNumeroActual(int numeroActual) {
        this.numeroActual = numeroActual;
    }

    public int getLongitudNumero() {
        return longitudNumero;
    }

    public void setLongitudNumero(int longitudNumero) {
        this.longitudNumero = longitudNumero;
    }

    public boolean isTieneImpuesto() {
        return tieneImpuesto;
    }

    public void setTieneImpuesto(boolean tieneImpuesto) {
        this.tieneImpuesto = tieneImpuesto;
    }

    public double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
