package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.ventas.accesoDatos.MaquinaDAO;
import com.sge.modulos.ventas.entidades.Maquina;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MaquinaDTO extends BaseDTO {

    MaquinaDAO maquinaDAO;

    public MaquinaDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Maquina> ObtenerMaquinas(String filtro) {
        List<Maquina> lista;
        try {
            maquinaDAO = new MaquinaDAO();
            maquinaDAO.AbrirSesion();
            lista = maquinaDAO.ObtenerMaquinas(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            maquinaDAO.CerrarSesion();
        }
        return lista;
    }

    public Maquina ObtenerMaquina(int idMaquina) {
        Maquina maquina = null;
        try {
            maquinaDAO = new MaquinaDAO();
            maquinaDAO.AbrirSesion();
            maquina = maquinaDAO.ObtenerPorId(Maquina.class, idMaquina);
        } catch (Exception e) {
            throw e;
        } finally {
            maquinaDAO.CerrarSesion();
        }
        return maquina;
    }

    public boolean RegistrarMaquina(Maquina maquina) {
        try {
            maquinaDAO = new MaquinaDAO();
            maquinaDAO.IniciarTransaccion();
            maquinaDAO.Agregar(maquina);
            maquinaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            maquinaDAO.AbortarTransaccion();
            throw e;
        } finally {
            maquinaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarMaquina(Maquina maquina) {
        try {
            maquinaDAO = new MaquinaDAO();
            maquinaDAO.IniciarTransaccion();
            maquinaDAO.ActualizarMaquina(maquina);
            maquinaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            maquinaDAO.AbortarTransaccion();
            throw e;
        } finally {
            maquinaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarMaquina(int idMaquina) {
        try {
            maquinaDAO = new MaquinaDAO();
            maquinaDAO.IniciarTransaccion();
            maquinaDAO.EliminarMaquina(idMaquina);
            maquinaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            maquinaDAO.AbortarTransaccion();
            throw e;
        } finally {
            maquinaDAO.CerrarSesion();
        }
        return true;
    }
}
