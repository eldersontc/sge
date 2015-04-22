package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.MaquinaDAO;
import com.sge.modulos.ventas.entidades.Maquina;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MaquinaDTO {
    
    MaquinaDAO maquinaDAO;

    public List<Object[]> ObtenerMaquinas(String filtro) {
        List<Object[]> lista;
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