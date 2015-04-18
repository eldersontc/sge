package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.ValorDefinidoDAO;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ValorDefinidoDTO {

    ValorDefinidoDAO valorDefinidoDAO;

    public List<Object[]> ObtenerValoresDefinidos(String filtro) {
        List<Object[]> lista;
        try {
            valorDefinidoDAO = new ValorDefinidoDAO();
            valorDefinidoDAO.AbrirSesision();
            lista = valorDefinidoDAO.ObtenerValoresDefinidos(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            valorDefinidoDAO.CerrarSesion();
        }
        return lista;
    }

    public ValorDefinido ObtenerValorDefinido(int idValorDefinido) {
        ValorDefinido valorDefinido;
        try {
            valorDefinidoDAO = new ValorDefinidoDAO();
            valorDefinidoDAO.AbrirSesision();
            valorDefinido = valorDefinidoDAO.ObtenerPorId(ValorDefinido.class, idValorDefinido);
        } catch (Exception e) {
            throw e;
        } finally {
            valorDefinidoDAO.CerrarSesion();
        }
        return valorDefinido;
    }

    public ValorDefinido ObtenerValorDefinidoPorUsuarioYEntidad(int idUsuario, int idEntidad) {
        ValorDefinido valorDefinido = null;
        try {
            valorDefinidoDAO = new ValorDefinidoDAO();
            valorDefinidoDAO.AbrirSesision();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idUsuario", idUsuario});
            filtros.add(new Object[]{"idEntidad", idEntidad});
            List<ValorDefinido> valoresDefinidos = valorDefinidoDAO.ObtenerLista(ValorDefinido.class, filtros);
            if (!valoresDefinidos.isEmpty()) {
                valorDefinido = valoresDefinidos.get(0);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            valorDefinidoDAO.CerrarSesion();
        }
        return valorDefinido;
    }

    public boolean RegistrarValorDefinido(ValorDefinido valorDefinido) {
        try {
            valorDefinidoDAO = new ValorDefinidoDAO();
            valorDefinidoDAO.IniciarTransaccion();
            valorDefinidoDAO.Agregar(valorDefinido);
            valorDefinidoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            valorDefinidoDAO.AbortarTransaccion();
            throw e;
        } finally {
            valorDefinidoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarValorDefinido(ValorDefinido valorDefinido) {
        try {
            valorDefinidoDAO = new ValorDefinidoDAO();
            valorDefinidoDAO.IniciarTransaccion();
            valorDefinidoDAO.ActualizarValorDefinido(valorDefinido.getIdValorDefinido(), valorDefinido.getIdUsuario(), valorDefinido.getUsuario(), valorDefinido.getIdEntidad(), valorDefinido.getJson(), valorDefinido.isActivo());
            valorDefinidoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            valorDefinidoDAO.AbortarTransaccion();
            throw e;
        } finally {
            valorDefinidoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarValorDefinido(int idValorDefinido) {
        try {
            valorDefinidoDAO = new ValorDefinidoDAO();
            valorDefinidoDAO.IniciarTransaccion();
            valorDefinidoDAO.EliminarValorDefinido(idValorDefinido);
            valorDefinidoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            valorDefinidoDAO.AbortarTransaccion();
            throw e;
        } finally {
            valorDefinidoDAO.CerrarSesion();
        }
        return true;
    }
}
