package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.PerfilDAO;
import com.sge.modulos.administracion.entidades.Perfil;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PerfilDTO {
    
    PerfilDAO perfilDAO;

    public List<Perfil> ObtenerPerfiles(String filtro) {
        List<Perfil> lista;
        try {
            perfilDAO = new PerfilDAO();
            perfilDAO.AbrirSesion();
            lista = perfilDAO.ObtenerPerfiles(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            perfilDAO.CerrarSesion();
        }
        return lista;
    }

    public boolean RegistrarPerfil(Perfil perfil) {
        try {
            perfilDAO = new PerfilDAO();
            perfilDAO.IniciarTransaccion();
            perfilDAO.Agregar(perfil);
            perfilDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            perfilDAO.AbortarTransaccion();
            throw e;
        } finally {
            perfilDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarPerfil(Perfil perfil) {
        try {
            perfilDAO = new PerfilDAO();
            perfilDAO.IniciarTransaccion();
            perfilDAO.ActualizarPerfil(perfil.getIdPerfil(), perfil.getNombre(), perfil.isActivo());
            perfilDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            perfilDAO.AbortarTransaccion();
            throw e;
        } finally {
            perfilDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarPerfil(int idPerfil) {
        try {
            perfilDAO = new PerfilDAO();
            perfilDAO.IniciarTransaccion();
            perfilDAO.EliminarPerfil(idPerfil);
            perfilDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            perfilDAO.AbortarTransaccion();
            throw e;
        } finally {
            perfilDAO.CerrarSesion();
        }
        return true;
    }
}
