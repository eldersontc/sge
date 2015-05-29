package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.MensajeDAO;
import com.sge.modulos.administracion.entidades.Mensaje;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MensajeDTO {

    MensajeDAO mensajeDAO;

    public List<Mensaje> ObtenerMensajes(int idUsuarioOrigen, int idUsuarioDestino) {
        List<Mensaje> lista;
        try {
            mensajeDAO = new MensajeDAO();
            mensajeDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idUsuarioOrigen", idUsuarioOrigen});
            filtros.add(new Object[]{"idUsuarioDestino", idUsuarioDestino});
            lista = mensajeDAO.ObtenerLista(Mensaje.class, filtros);
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
}
