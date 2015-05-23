package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.inventarios.accesoDatos.AlmacenDAO;
import com.sge.modulos.inventarios.entidades.Almacen;
import java.util.List;

/**
 *
 * @author elderson
 */
public class AlmacenDTO {
    
    AlmacenDAO almacenDAO;
    
    public List<Almacen> ObtenerAlmacenes(String filtro) {
        List<Almacen> lista;
        try {
            almacenDAO = new AlmacenDAO();
            almacenDAO.AbrirSesion();
            lista = almacenDAO.ObtenerAlmacenes(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            almacenDAO.CerrarSesion();
        }
        return lista;
    }
    
    public boolean RegistrarAlmacen(Almacen almacen) {
        try {
            almacenDAO = new AlmacenDAO();
            almacenDAO.IniciarTransaccion();
            almacenDAO.Agregar(almacen);
            almacenDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            almacenDAO.AbortarTransaccion();
            throw e;
        } finally {
            almacenDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean ActualizarAlmacen(Almacen almacen) {
        try {
            almacenDAO = new AlmacenDAO();
            almacenDAO.IniciarTransaccion();
            almacenDAO.ActualizarAlmacen(almacen.getIdAlmacen(), almacen.getCodigo(), almacen.getDescripcion(), almacen.isActivo());
            almacenDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            almacenDAO.AbortarTransaccion();
            throw e;
        } finally {
            almacenDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarAlmacen(int idAlmacen) {
        try {
            almacenDAO = new AlmacenDAO();
            almacenDAO.IniciarTransaccion();
            almacenDAO.EliminarAlmacen(idAlmacen);
            almacenDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            almacenDAO.AbortarTransaccion();
            throw e;
        } finally {
            almacenDAO.CerrarSesion();
        }
        return true;
    }
}
