package com.sge.modulos.inventarios.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.inventarios.accesoDatos.UnidadDAO;
import com.sge.modulos.inventarios.entidades.Unidad;
import java.util.List;

/**
 *
 * @author elderson
 */
public class UnidadDTO extends BaseDTO {

    UnidadDAO unidadDAO;

    public UnidadDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Unidad> ObtenerUnidades(String filtro) {
        List<Unidad> lista;
        try {
            unidadDAO = new UnidadDAO();
            unidadDAO.AbrirSesion();
            lista = unidadDAO.ObtenerUnidades(getFiltro(unidadDAO, 23, filtro));
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
