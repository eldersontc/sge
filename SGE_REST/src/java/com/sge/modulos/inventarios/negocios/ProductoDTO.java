package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.inventarios.accesoDatos.ProductoAlmacenDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoUnidadDAO;
import com.sge.modulos.inventarios.entidades.Producto;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import com.sge.modulos.inventarios.entidades.ProductoUnidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoDTO {

    ProductoDAO productoDAO;
    ProductoAlmacenDAO productoAlmacenDAO;
    ProductoUnidadDAO productoUnidadDAO;

    public List<Object[]> ObtenerProductos() {
        List<Object[]> lista;
        try {
            productoDAO = new ProductoDAO();
            productoDAO.AbrirSesion();
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
            productoDAO.AbrirSesion();
            Producto producto = productoDAO.ObtenerPorId(Producto.class, idProducto);
            lista.add(producto);
            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            List<Object[]> productoUnidades = productoUnidadDAO.ObtenerProductoUnidadesPorIdProducto(idProducto);
            lista.add(productoUnidades);
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

    public boolean RegistrarProducto(Producto producto, ProductoUnidad[] productoUnidades, ProductoAlmacen[] productoAlmacenes) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.Agregar(producto);

            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            for (ProductoUnidad productoUnidad : productoUnidades) {
                productoUnidad.setProducto(producto);
                productoUnidadDAO.Agregar(productoUnidad);
            }

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

    public boolean ActualizarProducto(Producto producto, ProductoUnidad[] productoUnidades, ProductoAlmacen[] productoAlmacenes) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.ActualizarProducto(producto.getIdProducto(), producto.getCodigo(), producto.getDescripcion(), producto.isInventarios(), producto.isCompras(), producto.isVentas(), producto.isActivo());

            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            for (ProductoUnidad productoUnidad : productoUnidades) {
                if(productoUnidad.isAgregar()){
                    productoUnidad.setProducto(producto);
                    productoUnidadDAO.Agregar(productoUnidad);
                }
                if(productoUnidad.isActualizar()){
                    productoUnidadDAO.ActualizarProductoUnidad(productoUnidad.getIdProductoUnidad(), productoUnidad.getFactor());
                }
                if(productoUnidad.isEliminar()){
                    productoUnidadDAO.EliminarProductoUnidad(productoUnidad.getIdProductoUnidad());
                }
            }

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

            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            productoUnidadDAO.EliminarProductoUnidadPorIdProducto(idProducto);

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
    
    public List<Producto> ObtenerProductoUnidades(Producto[] productos){
        List<Producto> lista = new ArrayList<>();
        try {
            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AbrirSesion();
            for (Producto producto : productos) {
                List<Object[]> productoUnidades = productoUnidadDAO.ObtenerProductoUnidadesPorIdProducto(producto.getIdProducto());
                producto.setProductoUnidades(productoUnidades);
                lista.add(producto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            productoUnidadDAO.CerrarSesion();
        }
        return lista;
    }
}
