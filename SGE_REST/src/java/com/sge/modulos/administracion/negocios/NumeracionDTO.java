package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.administracion.entidades.Numeracion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class NumeracionDTO {
    
    NumeracionDAO numeracionDAO;
    
    public List<Object[]> ObtenerNumeraciones(String filtro) {
        List<Object[]> lista;
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AbrirSesion();
            lista = numeracionDAO.ObtenerNumeraciones(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            numeracionDAO.CerrarSesion();
        }
        return lista;
    }
    
    public List<Numeracion> ObtenerNumeracionesPorEntidad(int idEntidad) {
        List<Numeracion> lista;
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idEntidad", idEntidad});
            lista = numeracionDAO.ObtenerLista(Numeracion.class, filtros);
        } catch (Exception e) {
            throw e;
        } finally {
            numeracionDAO.CerrarSesion();
        }
        return lista;
    }
    
    public Numeracion ObtenerNumeracion(int idNumeracion) {
        Numeracion numeracion;
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AbrirSesion();
            numeracion = numeracionDAO.ObtenerPorId(Numeracion.class, idNumeracion);
        } catch (Exception e) {
            throw e;
        } finally {
            numeracionDAO.CerrarSesion();
        }
        return numeracion;
    }
    
    public boolean RegistrarNumeracion(Numeracion numeracion) {
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.IniciarTransaccion();
            numeracionDAO.Agregar(numeracion);
            numeracionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            numeracionDAO.AbortarTransaccion();
            throw e;
        } finally {
            numeracionDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean ActualizarNumeracion(Numeracion numeracion) {
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.IniciarTransaccion();
            numeracionDAO.ActualizarNumeracion(numeracion.getIdNumeracion(), numeracion.getDescripcion(), numeracion.getIdEntidad(), numeracion.isManual(), numeracion.getSerie(), numeracion.getNumeroActual(), numeracion.getLongitudNumero(), numeracion.isTieneImpuesto(), numeracion.getPorcentajeImpuesto(),numeracion.isActivo());
            numeracionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            numeracionDAO.AbortarTransaccion();
            throw e;
        } finally {
            numeracionDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarNumeracion(int idNumeracion) {
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.IniciarTransaccion();
            numeracionDAO.EliminarNumeracion(idNumeracion);
            numeracionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            numeracionDAO.AbortarTransaccion();
            throw e;
        } finally {
            numeracionDAO.CerrarSesion();
        }
        return true;
    }
}
