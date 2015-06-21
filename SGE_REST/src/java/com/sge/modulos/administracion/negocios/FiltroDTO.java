package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.FiltroDAO;
import com.sge.modulos.administracion.entidades.Filtro;
import java.util.List;

/**
 *
 * @author elderson
 */
public class FiltroDTO extends BaseDTO {

    FiltroDAO filtroDAO;

    public FiltroDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Filtro> ObtenerFiltros(String filtro) {
        List<Filtro> lista;
        try {
            filtroDAO = new FiltroDAO();
            filtroDAO.AbrirSesion();
            lista = filtroDAO.ObtenerFiltros(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            filtroDAO.CerrarSesion();
        }
        return lista;
    }

    public boolean RegistrarFiltro(Filtro filtro) {
        try {
            filtroDAO = new FiltroDAO();
            filtroDAO.IniciarTransaccion();
            filtroDAO.Agregar(filtro);
            filtroDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            filtroDAO.AbortarTransaccion();
            throw e;
        } finally {
            filtroDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarFiltro(Filtro filtro) {
        try {
            filtroDAO = new FiltroDAO();
            filtroDAO.IniciarTransaccion();
            filtroDAO.ActualizarFiltro(filtro);
            filtroDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            filtroDAO.AbortarTransaccion();
            throw e;
        } finally {
            filtroDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarFiltro(int idFiltro) {
        try {
            filtroDAO = new FiltroDAO();
            filtroDAO.IniciarTransaccion();
            filtroDAO.EliminarFiltro(idFiltro);
            filtroDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            filtroDAO.AbortarTransaccion();
            throw e;
        } finally {
            filtroDAO.CerrarSesion();
        }
        return true;
    }
}
