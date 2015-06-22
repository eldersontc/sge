package com.sge.modulos.compras.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.compras.accesoDatos.ProveedorDAO;
import com.sge.modulos.compras.entidades.Proveedor;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProveedorDTO extends BaseDTO {

    ProveedorDAO proveedorDAO;

    public ProveedorDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Proveedor> ObtenerProveedores(String filtro) {
        List<Proveedor> lista;
        try {
            proveedorDAO = new ProveedorDAO();
            proveedorDAO.AbrirSesion();
            lista = proveedorDAO.ObtenerProveedores(getFiltro(proveedorDAO, 19, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            proveedorDAO.CerrarSesion();
        }
        return lista;
    }

    public Proveedor ObtenerProveedor(int idProveedor) {
        Proveedor proveedor;
        try {
            proveedorDAO = new ProveedorDAO();
            proveedorDAO.AbrirSesion();
            proveedor = proveedorDAO.ObtenerPorId(Proveedor.class, idProveedor);
        } catch (Exception e) {
            throw e;
        } finally {
            proveedorDAO.CerrarSesion();
        }
        return proveedor;
    }

    public boolean RegistrarProveedor(Proveedor proveedor) {
        try {
            proveedorDAO = new ProveedorDAO();
            proveedorDAO.IniciarTransaccion();
            proveedorDAO.Agregar(proveedor);
            proveedorDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            proveedorDAO.AbortarTransaccion();
            throw e;
        } finally {
            proveedorDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarProveedor(Proveedor proveedor) {
        try {
            proveedorDAO = new ProveedorDAO();
            proveedorDAO.IniciarTransaccion();
            proveedorDAO.ActualizarProveedor(proveedor.getIdProveedor(), proveedor.getRazonSocial(), proveedor.getTipoDocumentoIdentidad(), proveedor.getDocumentoIdentidad(), proveedor.getNombreComercial(), proveedor.isActivo());
            proveedorDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            proveedorDAO.AbortarTransaccion();
            throw e;
        } finally {
            proveedorDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarProveedor(int idProveedor) {
        try {
            proveedorDAO = new ProveedorDAO();
            proveedorDAO.IniciarTransaccion();
            proveedorDAO.EliminarProveedor(idProveedor);
            proveedorDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            proveedorDAO.AbortarTransaccion();
            throw e;
        } finally {
            proveedorDAO.CerrarSesion();
        }
        return true;
    }
}
