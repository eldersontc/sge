package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.inventarios.accesoDatos.ProductoAlmacenDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoDAO;
import com.sge.modulos.inventarios.entidades.Producto;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoDTO {

    ProductoDAO productoDAO;
    ProductoAlmacenDAO productoAlmacenDAO;

    public List<Object[]> ObtenerProductos() {
        List<Object[]> lista;
        try {
            productoDAO = new ProductoDAO();
            productoDAO.AbrirSesision();
            lista = productoDAO.ObtenerProductos();
        } catch (Exception e) {
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return lista;
    }

    public List<Object> ObtenerProducto(int idProducto) {
        List<Object> lista = new ArrayList<>();
        try {
            productoDAO = new ProductoDAO();
            productoDAO.AbrirSesision();
            Producto producto = productoDAO.ObtenerPorId(Producto.class, idProducto);
            lista.add(producto);
            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            List<Object[]> productoAlmacenes = productoAlmacenDAO.ObtenerProductoAlmacenesPorIdProducto(idProducto);
            lista.add(productoAlmacenes);
        } catch (Exception e) {
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return lista;
    }

    public boolean RegistrarProducto(Producto producto, ProductoAlmacen[] productoAlmacenes) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.Agregar(producto);
            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            for (ProductoAlmacen productoAlmacen : productoAlmacenes) {
                productoAlmacen.setProducto(producto);
                productoAlmacenDAO.Agregar(productoAlmacen);
            }
            productoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            productoDAO.AbortarTransaccion();
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarProducto(Producto producto, ProductoAlmacen[] productoAlmacenes) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.ActualizarProducto(producto.getIdProducto(), producto.getCodigo(), producto.getDescripcion(), producto.isInventarios(), producto.isCompras(), producto.isVentas(), producto.isActivo());
            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            for (ProductoAlmacen productoAlmacen : productoAlmacenes) {
                if (productoAlmacen.getIdProductoAlmacen() == 0) {
                    productoAlmacen.setProducto(producto);
                    productoAlmacenDAO.Agregar(productoAlmacen);
                } else {
                    productoAlmacenDAO.EliminarProductoAlmacen(productoAlmacen.getIdProductoAlmacen());
                }
            }
            productoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            productoDAO.AbortarTransaccion();
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarProducto(int idProducto) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.EliminarProducto(idProducto);
            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            productoAlmacenDAO.EliminarProductoAlmacenPorIdProducto(idProducto);
            productoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            productoDAO.AbortarTransaccion();
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return true;
    }
}
