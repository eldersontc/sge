package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class Provincia {
    
    private int idProvincia;
    private String nombre;
    private int idDepartamento;

    public Provincia() {
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
