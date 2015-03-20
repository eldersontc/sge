package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class Perfil {
    
    private int idPerfil;
    private String nombre;

    public Perfil() {
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
