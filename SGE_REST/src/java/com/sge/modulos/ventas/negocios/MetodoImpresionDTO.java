package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.ventas.accesoDatos.MetodoImpresionDAO;
import com.sge.modulos.ventas.entidades.MetodoImpresion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MetodoImpresionDTO extends BaseDTO {

    MetodoImpresionDAO metodoImpresionDAO;

    public MetodoImpresionDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<MetodoImpresion> ObtenerMetodosImpresion(String filtro) {
        List<MetodoImpresion> lista;
        try {
            metodoImpresionDAO = new MetodoImpresionDAO();
            metodoImpresionDAO.AbrirSesion();
            lista = metodoImpresionDAO.ObtenerMetodosImpresion(getFiltro(metodoImpresionDAO, 30, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            metodoImpresionDAO.CerrarSesion();
        }
        return lista;
    }

    public boolean RegistrarMetodoImpresion(MetodoImpresion metodoImpresion) {
        try {
            metodoImpresionDAO = new MetodoImpresionDAO();
            metodoImpresionDAO.IniciarTransaccion();
            metodoImpresionDAO.Agregar(metodoImpresion);
            metodoImpresionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            metodoImpresionDAO.AbortarTransaccion();
            throw e;
        } finally {
            metodoImpresionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarMetodoImpresion(MetodoImpresion metodoImpresion) {
        try {
            metodoImpresionDAO = new MetodoImpresionDAO();
            metodoImpresionDAO.IniciarTransaccion();
            metodoImpresionDAO.ActualizarMetodoImpresion(metodoImpresion.getIdMetodoImpresion(), metodoImpresion.getNombre(), metodoImpresion.getFactorPases(), metodoImpresion.getFactorCambios(), metodoImpresion.getFactorHorizontal(), metodoImpresion.getFactorVertical(), metodoImpresion.getLetras(), metodoImpresion.isActivo());
            metodoImpresionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            metodoImpresionDAO.AbortarTransaccion();
            throw e;
        } finally {
            metodoImpresionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarMetodoImpresion(int idMetodoImpresion) {
        try {
            metodoImpresionDAO = new MetodoImpresionDAO();
            metodoImpresionDAO.IniciarTransaccion();
            metodoImpresionDAO.EliminarMetodoImpresion(idMetodoImpresion);
            metodoImpresionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            metodoImpresionDAO.AbortarTransaccion();
            throw e;
        } finally {
            metodoImpresionDAO.CerrarSesion();
        }
        return true;
    }
}
