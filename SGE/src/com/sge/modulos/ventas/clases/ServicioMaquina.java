package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class ServicioMaquina {
    
    private int idServicioMaquina;
    private int idServicio;
    private int idMaquina;
    private String descripcionMaquina;
    private boolean agregar;
    private boolean eliminar;

    public ServicioMaquina() {
    }

    public int getIdServicioMaquina() {
        return idServicioMaquina;
    }

    public void setIdServicioMaquina(int idServicioMaquina) {
        this.idServicioMaquina = idServicioMaquina;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescripcionMaquina() {
        return descripcionMaquina;
    }

    public void setDescripcionMaquina(String descripcionMaquina) {
        this.descripcionMaquina = descripcionMaquina;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
}
