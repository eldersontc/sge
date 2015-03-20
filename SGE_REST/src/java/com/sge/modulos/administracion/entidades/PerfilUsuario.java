package com.sge.modulos.administracion.entidades;

/**
 *
 * @author elderson
 */
public class PerfilUsuario {
    
    private int idPerfilUsuario;
    private Perfil perfil;
    private Usuario usuario;

    public PerfilUsuario() {
    }

    public int getIdPerfilUsuario() {
        return idPerfilUsuario;
    }

    public void setIdPerfilUsuario(int idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
