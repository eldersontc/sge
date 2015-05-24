package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.FormaPagoDAO;
import com.sge.modulos.ventas.entidades.FormaPago;
import java.util.List;

/**
 *
 * @author elderson
 */
public class FormaPagoDTO {
    
    FormaPagoDAO formaPagoDAO;
    
    public List<FormaPago> ObtenerFormasPago(String filtro) {
        List<FormaPago> lista;
        try {
            formaPagoDAO = new FormaPagoDAO();
            formaPagoDAO.AbrirSesion();
            lista = formaPagoDAO.ObtenerFormasPago(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            formaPagoDAO.CerrarSesion();
        }
        return lista;
    }
    
    public boolean RegistrarFormaPago(FormaPago formaPago) {
        try {
            formaPagoDAO = new FormaPagoDAO();
            formaPagoDAO.IniciarTransaccion();
            formaPagoDAO.Agregar(formaPago);
            formaPagoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            formaPagoDAO.AbortarTransaccion();
            throw e;
        } finally {
            formaPagoDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean ActualizarFormaPago(FormaPago formaPago) {
        try {
            formaPagoDAO = new FormaPagoDAO();
            formaPagoDAO.IniciarTransaccion();
            formaPagoDAO.ActualizarFormaPago(formaPago.getIdFormaPago(), formaPago.getDescripcion(), formaPago.isCredito(), formaPago.getDias(), formaPago.isActivo());
            formaPagoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            formaPagoDAO.AbortarTransaccion();
            throw e;
        } finally {
            formaPagoDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarFormaPago(int idFormaPago) {
        try {
            formaPagoDAO = new FormaPagoDAO();
            formaPagoDAO.IniciarTransaccion();
            formaPagoDAO.EliminarFormaPago(idFormaPago);
            formaPagoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            formaPagoDAO.AbortarTransaccion();
            throw e;
        } finally {
            formaPagoDAO.CerrarSesion();
        }
        return true;
    }
}
