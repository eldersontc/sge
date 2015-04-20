package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.UsuarioDAO;
import com.sge.modulos.administracion.entidades.Usuario;
import java.util.List;

/**
 *
 * @author elderson
 */
public class UsuarioDTO {

    UsuarioDAO usuarioDAO;

    public List<Object[]> ObtenerUsuarios(String filtro) {
        List<Object[]> lista;
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.AbrirSesion();
            lista = usuarioDAO.ObtenerUsuarios(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            usuarioDAO.CerrarSesion();
        }
        return lista;
    }

    public boolean RegistrarUsuario(Usuario usuario) {
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.IniciarTransaccion();
            usuarioDAO.Agregar(usuario);
            usuarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            usuarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            usuarioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarUsuario(Usuario usuario) {
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.IniciarTransaccion();
            usuarioDAO.ActualizarUsuario(usuario.getIdUsuario(), usuario.getUsuario(), usuario.getClave(), usuario.isActivo());
            usuarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            usuarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            usuarioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarUsuario(int idUsuario) {
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.IniciarTransaccion();
            usuarioDAO.EliminarUsuario(idUsuario);
            usuarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            usuarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            usuarioDAO.CerrarSesion();
        }
        return true;
    }
}
