package com.sge.modulos.finanzas.negocios;

import com.sge.modulos.finanzas.accesoDatos.CajaDAO;
import com.sge.modulos.finanzas.entidades.Caja;
import java.util.List;

/**
 *
 * @author elderson
 */
public class CajaDTO {
    
    CajaDAO cajaDAO;
    
    public List<Caja> ObtenerCajas(String filtro) {
        List<Caja> lista;
        try {
            cajaDAO = new CajaDAO();
            cajaDAO.AbrirSesion();
            lista = cajaDAO.ObtenerCajas(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            cajaDAO.CerrarSesion();
        }
        return lista;
    }
    
    public boolean RegistrarCaja(Caja caja) {
        try {
            cajaDAO = new CajaDAO();
            cajaDAO.IniciarTransaccion();
            cajaDAO.Agregar(caja);
            cajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            cajaDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean ActualizarCaja(Caja caja) {
        try {
            cajaDAO = new CajaDAO();
            cajaDAO.IniciarTransaccion();
            cajaDAO.ActualizarCaja(caja.getIdCaja(), caja.getDescripcion(), caja.getIdMoneda(), caja.getSimboloMoneda(), caja.isActivo());
            cajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            cajaDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarCaja(int idCaja) {
        try {
            cajaDAO = new CajaDAO();
            cajaDAO.IniciarTransaccion();
            cajaDAO.EliminarCaja(idCaja);
            cajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            cajaDAO.CerrarSesion();
        }
        return true;
    }
}
