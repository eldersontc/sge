package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.inventarios.accesoDatos.SalidaInventarioDAO;
import com.sge.modulos.inventarios.accesoDatos.ItemSalidaInventarioDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoAlmacenDAO;
import com.sge.modulos.inventarios.entidades.SalidaInventario;
import com.sge.modulos.inventarios.entidades.ItemSalidaInventario;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SalidaInventarioDTO {

    SalidaInventarioDAO salidaInventarioDAO;
    ItemSalidaInventarioDAO itemSalidaInventarioDAO;
    ProductoAlmacenDAO productoAlmacenDAO;
    NumeracionDAO numeracionDAO;

    public List<SalidaInventario> ObtenerSalidaInventarios(String filtro) {
        List<SalidaInventario> lista;
        try {
            salidaInventarioDAO = new SalidaInventarioDAO();
            salidaInventarioDAO.AbrirSesion();
            lista = salidaInventarioDAO.ObtenerSalidaInventarios(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            salidaInventarioDAO.CerrarSesion();
        }
        return lista;
    }

    public SalidaInventario ObtenerSalidaInventario(int idSalidaInventario) {
        SalidaInventario salidaInventario;
        try {
            salidaInventarioDAO = new SalidaInventarioDAO();
            salidaInventarioDAO.AbrirSesion();
            salidaInventario = salidaInventarioDAO.ObtenerPorId(SalidaInventario.class, idSalidaInventario);
            itemSalidaInventarioDAO = new ItemSalidaInventarioDAO();
            itemSalidaInventarioDAO.AsignarSesion(salidaInventarioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSalidaInventario", idSalidaInventario});
            List<ItemSalidaInventario> items = itemSalidaInventarioDAO.ObtenerLista(ItemSalidaInventario.class, filtros);
            salidaInventario.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            salidaInventarioDAO.CerrarSesion();
        }
        return salidaInventario;
    }

    public boolean RegistrarSalidaInventario(SalidaInventario salidaInventario) {
        try {
            salidaInventarioDAO = new SalidaInventarioDAO();
            salidaInventarioDAO.IniciarTransaccion();

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(salidaInventarioDAO);

            if (!salidaInventario.isNumeracionManual()) {
                salidaInventario.setNumero(numeracionDAO.GenerarNumeracion(salidaInventario.getIdNumeracion()));
            }

            salidaInventarioDAO.Agregar(salidaInventario);

            itemSalidaInventarioDAO = new ItemSalidaInventarioDAO();
            itemSalidaInventarioDAO.AsignarSesion(salidaInventarioDAO);

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(salidaInventarioDAO);

            for (ItemSalidaInventario item : salidaInventario.getItems()) {
                item.setIdSalidaInventario(salidaInventario.getIdSalidaInventario());
                item.setIdAlmacen(salidaInventario.getIdAlmacen());
                itemSalidaInventarioDAO.Agregar(item);
                productoAlmacenDAO.ActualizarStockFisico(item.getIdProducto(), item.getIdAlmacen(), item.getCantidad() * item.getFactor() * -1);
            }

            salidaInventarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            salidaInventarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            salidaInventarioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarSalidaInventario(int idSalidaInventario) {
        try {
            salidaInventarioDAO = new SalidaInventarioDAO();
            salidaInventarioDAO.IniciarTransaccion();
            salidaInventarioDAO.EliminarSalidaInventario(idSalidaInventario);

            itemSalidaInventarioDAO = new ItemSalidaInventarioDAO();
            itemSalidaInventarioDAO.AsignarSesion(salidaInventarioDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSalidaInventario", idSalidaInventario});
            List<ItemSalidaInventario> items = itemSalidaInventarioDAO.ObtenerLista(ItemSalidaInventario.class, filtros);

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(salidaInventarioDAO);

            for (ItemSalidaInventario item : items) {
                productoAlmacenDAO.ActualizarStockFisico(item.getIdProducto(), item.getIdAlmacen(), item.getCantidad() * item.getFactor());
            }

            itemSalidaInventarioDAO.EliminarItemSalidaInventarioPorIdSalidaInventario(idSalidaInventario);

            salidaInventarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            salidaInventarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            salidaInventarioDAO.CerrarSesion();
        }
        return true;
    }

    public ItemSalidaInventario[] ObtenerStockFisicoItems(ItemSalidaInventario[] items) {
        try {
            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AbrirSesion();
            for (ItemSalidaInventario item : items) {
                List<Object[]> filtros = new ArrayList<>();
                filtros.add(new Object[]{"idAlmacen", item.getIdAlmacen()});
                filtros.add(new Object[]{"idProducto", item.getIdProducto()});
                List<ProductoAlmacen> productoAlmacenes = productoAlmacenDAO.ObtenerLista(ProductoAlmacen.class, filtros);
                if(!productoAlmacenes.isEmpty()){
                    item.setCantidadMaxima(productoAlmacenes.get(0).getStockFisico());
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            productoAlmacenDAO.CerrarSesion();
        }
        return items;
    }
}
