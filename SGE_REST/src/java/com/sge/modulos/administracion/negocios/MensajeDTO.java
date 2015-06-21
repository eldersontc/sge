package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.MensajeDAO;
import com.sge.modulos.administracion.entidades.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MensajeDTO extends BaseDTO {

    MensajeDAO mensajeDAO;

    public MensajeDTO(int idUsuario) {
        super(idUsuario);
    }

    public int ObtenerMensajesSinLeer() {
        int mensajesSinLeer;
        try {
            mensajeDAO = new MensajeDAO();
            mensajeDAO.AbrirSesion();
            mensajesSinLeer = mensajeDAO.ObtenerMensajesSinLeer(getIdUsuario());
        } catch (Exception e) {
            throw e;
        } finally {
            mensajeDAO.CerrarSesion();
        }
        return mensajesSinLeer;
    }

    public List<Mensaje> ObtenerMensajesPorUsuarioOrigenYDestino(int idUsuarioOrigen, int idUsuarioDestino) {
        List<Mensaje> lista;
        try {
            mensajeDAO = new MensajeDAO();
            mensajeDAO.AbrirSesion();
            lista = mensajeDAO.ObtenerMensajesPorUsuarioOrigenYDestino(idUsuarioOrigen, idUsuarioDestino);
        } catch (Exception e) {
            throw e;
        } finally {
            mensajeDAO.CerrarSesion();
        }
        return lista;
    }

    public boolean RegistrarMensaje(Mensaje mensaje) {
        try {
            mensajeDAO = new MensajeDAO();
            mensajeDAO.IniciarTransaccion();
            mensaje.setFechaEnvio(new Date());
            mensajeDAO.Agregar(mensaje);
            mensajeDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            mensajeDAO.AbortarTransaccion();
            throw e;
        } finally {
            mensajeDAO.CerrarSesion();
        }
        return true;
    }

    public boolean CambiarALeido(int idUsuarioOrigen, int idUsuarioDestino) {
        try {
            mensajeDAO = new MensajeDAO();
            mensajeDAO.IniciarTransaccion();
            mensajeDAO.CambiarALeido(idUsuarioOrigen, idUsuarioDestino);
            mensajeDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            mensajeDAO.AbortarTransaccion();
            throw e;
        } finally {
            mensajeDAO.CerrarSesion();
        }
        return true;
    }
}
