package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.inventarios.accesoDatos.UnidadDAO;
import com.sge.modulos.inventarios.entidades.Unidad;
import java.util.List;

/**
 *
 * @author elderson
 */
public class UnidadDTO {
    
    UnidadDAO unidadDAO;
    
    public List<Object[]> ObtenerUnidades() {
        List<Object[]> lista;
        try {
            unidadDAO = new UnidadDAO();
            unidadDAO.AbrirSesision();
            lista = unidadDAO.ObtenerUnidades();
        } catch (Exception e) {
            throw e;
        } finally {
            unidadDAO.CerrarSesion();
        }
        return lista;
    }
    
    public boolean RegistrarUnidad(Unidad unidad) {
        try {
            unidadDAO = new UnidadDAO();
            unidadDAO.IniciarTransaccion();
            unidadDAO.Agregar(unidad);
            unidadDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            unidadDAO.AbortarTransaccion();
            throw e;
        } finally {
            unidadDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean ActualizarUnidad(Unidad unidad) {
        try {
            unidadDAO = new UnidadDAO();
            unidadDAO.IniciarTransaccion();
            unidadDAO.ActualizarUnidad(unidad.getIdUnidad(), unidad.getAbreviacion(), unidad.getDescripcion(), unidad.isActivo());
            unidadDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            unidadDAO.AbortarTransaccion();
            throw e;
        } finally {
            unidadDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarUnidad(int idUnidad) {
        try {
            unidadDAO = new UnidadDAO();
            unidadDAO.IniciarTransaccion();
            unidadDAO.EliminarUnidad(idUnidad);
            unidadDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            unidadDAO.AbortarTransaccion();
            throw e;
        } finally {
            unidadDAO.CerrarSesion();
        }
        return true;
    }
}
