package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class Entidad {
    
    private int idEntidad;
    private String nombre;
    private String formulario;
    private boolean reporte;
    private boolean valorDefinido;
    private boolean activo;

    public Entidad() {
    }

    public int getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(int idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormulario() {
        return formulario;
    }

    public void setFormulario(String formulario) {
        this.formulario = formulario;
    }

    public boolean isReporte() {
        return reporte;
    }

    public void setReporte(boolean reporte) {
        this.reporte = reporte;
    }

    public boolean isValorDefinido() {
        return valorDefinido;
    }

    public void setValorDefinido(boolean valorDefinido) {
        this.valorDefinido = valorDefinido;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
