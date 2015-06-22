package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.EmpleadoDAO;
import com.sge.modulos.administracion.entidades.Empleado;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EmpleadoDTO extends BaseDTO {

    EmpleadoDAO empleadoDAO;

    public EmpleadoDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Empleado> ObtenerEmpleados(String filtro) {
        List<Empleado> lista;
        try {
            empleadoDAO = new EmpleadoDAO();
            empleadoDAO.AbrirSesion();
            lista = empleadoDAO.ObtenerEmpleados(getFiltro(empleadoDAO, 14, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            empleadoDAO.CerrarSesion();
        }
        return lista;
    }

    public Empleado ObtenerEmpleado(int idEmpleado) {
        Empleado empleado;
        try {
            empleadoDAO = new EmpleadoDAO();
            empleadoDAO.AbrirSesion();
            empleado = empleadoDAO.ObtenerPorId(Empleado.class, idEmpleado);
        } catch (Exception e) {
            throw e;
        } finally {
            empleadoDAO.CerrarSesion();
        }
        return empleado;
    }

    public boolean RegistrarEmpleado(Empleado empleado) {
        try {
            empleadoDAO = new EmpleadoDAO();
            empleadoDAO.IniciarTransaccion();
            empleadoDAO.Agregar(empleado);
            empleadoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            empleadoDAO.AbortarTransaccion();
            throw e;
        } finally {
            empleadoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarEmpleado(Empleado empleado) {
        try {
            empleadoDAO = new EmpleadoDAO();
            empleadoDAO.IniciarTransaccion();
            empleadoDAO.ActualizarEmpleado(empleado.getIdEmpleado(), empleado.getCodigo(), empleado.getNombre(), empleado.getApellidoPaterno(), empleado.getApellidoMaterno(), empleado.getTipoDocumentoIdentidad(), empleado.getDocumentoIdentidad(), empleado.isActivo());
            empleadoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            empleadoDAO.AbortarTransaccion();
            throw e;
        } finally {
            empleadoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarEmpleado(int idEmpleado) {
        try {
            empleadoDAO = new EmpleadoDAO();
            empleadoDAO.IniciarTransaccion();
            empleadoDAO.EliminarEmpleado(idEmpleado);
            empleadoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            empleadoDAO.AbortarTransaccion();
            throw e;
        } finally {
            empleadoDAO.CerrarSesion();
        }
        return true;
    }
}
