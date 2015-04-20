package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.MonedaDAO;
import com.sge.modulos.administracion.entidades.Moneda;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MonedaDTO {
    
    MonedaDAO monedaDAO;
    
    public List<Object[]> ObtenerMonedas() {
        List<Object[]> lista;
        try {
            monedaDAO = new MonedaDAO();
            monedaDAO.AbrirSesion();
            lista = monedaDAO.ObtenerMonedas();
        } catch (Exception e) {
            throw e;
        } finally {
            monedaDAO.CerrarSesion();
        }
        return lista;
    }
    
    public boolean RegistrarMoneda(Moneda moneda) {
        try {
            monedaDAO = new MonedaDAO();
            monedaDAO.IniciarTransaccion();
            monedaDAO.Agregar(moneda);
            monedaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            monedaDAO.AbortarTransaccion();
            throw e;
        } finally {
            monedaDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean ActualizarMoneda(Moneda moneda) {
        try {
            monedaDAO = new MonedaDAO();
            monedaDAO.IniciarTransaccion();
            monedaDAO.ActualizarMoneda(moneda.getIdMoneda(), moneda.getSimbolo(), moneda.getNombre(), moneda.isActivo());
            monedaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            monedaDAO.AbortarTransaccion();
            throw e;
        } finally {
            monedaDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarMoneda(int idMoneda) {
        try {
            monedaDAO = new MonedaDAO();
            monedaDAO.IniciarTransaccion();
            monedaDAO.EliminarMoneda(idMoneda);
            monedaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            monedaDAO.AbortarTransaccion();
            throw e;
        } finally {
            monedaDAO.CerrarSesion();
        }
        return true;
    }
}
