package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.administracion.entidades.Numeracion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class NumeracionDTO extends BaseDTO {

    NumeracionDAO numeracionDAO;

    public NumeracionDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Numeracion> ObtenerNumeraciones(String filtro) {
        List<Numeracion> lista;
        try {
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AbrirSesion();
            lista = numeracionDAO.ObtenerNumeraciones(getFiltro(numeracionDAO, 16, filtro));
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
            numeracionDAO.ActualizarNumeracion(numeracion.getIdNumeracion(), numeracion.getDescripcion(), numeracion.getIdEntidad(), numeracion.getNombreEntidad(), numeracion.isManual(), numeracion.getSerie(), numeracion.getNumeroActual(), numeracion.getLongitudNumero(), numeracion.isTieneImpuesto(), numeracion.getPorcentajeImpuesto(), numeracion.isActivo());
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
