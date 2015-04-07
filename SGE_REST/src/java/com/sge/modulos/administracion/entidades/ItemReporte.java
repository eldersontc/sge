package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class ItemReporte {
    
    private int idItemReporte;
    private int idReporte;
    private String nombre;
    private boolean asignarId;
    private String valor;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;
    
    public ItemReporte() {
    }

    public int getIdItemReporte() {
        return idItemReporte;
    }

    public void setIdItemReporte(int idItemReporte) {
        this.idItemReporte = idItemReporte;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAsignarId() {
        return asignarId;
    }

    public void setAsignarId(boolean asignarId) {
        this.asignarId = asignarId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
