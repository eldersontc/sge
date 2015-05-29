package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.MensajeDAO;
import com.sge.modulos.administracion.accesoDatos.UsuarioDAO;
import com.sge.modulos.administracion.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class UsuarioDTO {

    UsuarioDAO usuarioDAO;
    MensajeDAO mensajeDAO;

    public List<Usuario> ObtenerUsuarios(String filtro) {
        List<Usuario> lista;
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

    public List<Usuario> ObtenerUsuariosConMensajesSinLeer(int idUsuarioDestino) {
        List<Usuario> lista;
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.AbrirSesion();
            
            mensajeDAO = new MensajeDAO();
            mensajeDAO.AbrirSesion();
            
            lista = usuarioDAO.ObtenerUsuarios(String.format("WHERE Usuario.idUsuario NOT IN (%d)", idUsuarioDestino));
            
            for (Usuario usuario : lista) {
                
            }
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
            usuarioDAO.ActualizarUsuario(usuario.getIdUsuario(), usuario.getUsuario(), usuario.getClave(), usuario.getIdPerfil(), usuario.getNombrePerfil(), usuario.isActivo());
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
    
    public boolean ConectarUsuario(Usuario usuario) {
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.IniciarTransaccion();
            usuarioDAO.ConectarUsuario(usuario.getIdUsuario(), usuario.getIp());
            usuarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            usuarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            usuarioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean DesconectarUsuario(int idUsuario) {
        try {
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.IniciarTransaccion();
            usuarioDAO.DesconectarUsuario(idUsuario);
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
