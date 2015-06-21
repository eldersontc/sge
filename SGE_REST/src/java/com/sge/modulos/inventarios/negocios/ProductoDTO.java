package com.sge.modulos.inventarios.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.inventarios.accesoDatos.ProductoAlmacenDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoUnidadDAO;
import com.sge.modulos.inventarios.entidades.Producto;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import com.sge.modulos.inventarios.entidades.ProductoUnidad;
import com.sge.modulos.inventarios.entidades.SeleccionProducto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoDTO extends BaseDTO {

    ProductoDAO productoDAO;
    ProductoAlmacenDAO productoAlmacenDAO;
    ProductoUnidadDAO productoUnidadDAO;

    public ProductoDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Producto> ObtenerProductos(String filtro) {
        List<Producto> lista;
        try {
            productoDAO = new ProductoDAO();
            productoDAO.AbrirSesion();
            lista = productoDAO.ObtenerProductos(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return lista;
    }

    public List<SeleccionProducto> ObtenerProductosPorAlmacen(String filtro) {
        List<SeleccionProducto> lista;
        try {
            productoDAO = new ProductoDAO();
            productoDAO.AbrirSesion();
            lista = productoDAO.ObtenerProductosPorAlmacen(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return lista;
    }

    public Producto ObtenerProducto(int idProducto) {
        Producto producto = null;
        try {
            productoDAO = new ProductoDAO();
            productoDAO.AbrirSesion();
            producto = productoDAO.ObtenerPorId(Producto.class, idProducto);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idProducto", idProducto});

            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            List<ProductoUnidad> unidades = productoUnidadDAO.ObtenerLista(ProductoUnidad.class, filtros);
            producto.setUnidades(unidades);

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            List<ProductoAlmacen> almacenes = productoAlmacenDAO.ObtenerLista(ProductoAlmacen.class, filtros);
            producto.setAlmacenes(almacenes);

        } catch (Exception e) {
            throw e;
        } finally {
            productoDAO.CerrarSesion();
        }
        return producto;
    }

    public boolean RegistrarProducto(Producto producto) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.Agregar(producto);

            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            for (ProductoUnidad productoUnidad : producto.getUnidades()) {
                productoUnidad.setIdProducto(producto.getIdProducto());
                productoUnidadDAO.Agregar(productoUnidad);
            }

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            for (ProductoAlmacen productoAlmacen : producto.getAlmacenes()) {
                productoAlmacen.setIdProducto(producto.getIdProducto());
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

    public boolean ActualizarProducto(Producto producto) {
        try {
            productoDAO = new ProductoDAO();
            productoDAO.IniciarTransaccion();
            productoDAO.ActualizarProducto(producto);

            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AsignarSesion(productoDAO);
            for (ProductoUnidad productoUnidad : producto.getUnidades()) {
                if (productoUnidad.isAgregar()) {
                    productoUnidad.setIdProducto(producto.getIdProducto());
                    productoUnidadDAO.Agregar(productoUnidad);
                }
                if (productoUnidad.isActualizar()) {
                    productoUnidadDAO.ActualizarProductoUnidad(productoUnidad.getIdProductoUnidad(), productoUnidad.getFactor(), productoUnidad.isBase());
                }
                if (productoUnidad.isEliminar()) {
                    productoUnidadDAO.EliminarProductoUnidad(productoUnidad.getIdProductoUnidad());
                }
            }

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(productoDAO);
            for (ProductoAlmacen productoAlmacen : producto.getAlmacenes()) {
                if (productoAlmacen.getIdProductoAlmacen() == 0) {
                    productoAlmacen.setIdProducto(producto.getIdProducto());
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

    public List<Producto> ObtenerUnidadesPorProductos(Producto[] productos) {
        List<Producto> lista = new ArrayList<>();
        try {
            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AbrirSesion();

            for (Producto producto : productos) {
                List<Object[]> filtros = new ArrayList<>();
                filtros.add(new Object[]{"idProducto", producto.getIdProducto()});

                List<ProductoUnidad> unidades = productoUnidadDAO.ObtenerLista(ProductoUnidad.class, filtros);
                producto.setUnidades(unidades);

                lista.add(producto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            productoUnidadDAO.CerrarSesion();
        }
        return lista;
    }

    public List<ProductoUnidad> ObtenerProductoUnidades(String filtro) {
        List<ProductoUnidad> lista = new ArrayList<>();
        try {
            productoUnidadDAO = new ProductoUnidadDAO();
            productoUnidadDAO.AbrirSesion();
            lista = productoUnidadDAO.ObtenerProductoUnidades(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            productoUnidadDAO.CerrarSesion();
        }
        return lista;
    }

    public List<ProductoAlmacen> ObtenerProductoAlmacenes(String filtro) {
        List<ProductoAlmacen> lista = new ArrayList<>();
        try {
            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AbrirSesion();
            lista = productoAlmacenDAO.ObtenerProductoAlmacenes(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            productoAlmacenDAO.CerrarSesion();
        }
        return lista;
    }
}
