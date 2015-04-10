package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.inventarios.accesoDatos.EntradaInventarioDAO;
import com.sge.modulos.inventarios.accesoDatos.ItemEntradaInventarioDAO;
import com.sge.modulos.inventarios.accesoDatos.ProductoAlmacenDAO;
import com.sge.modulos.inventarios.entidades.EntradaInventario;
import com.sge.modulos.inventarios.entidades.ItemEntradaInventario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaInventarioDTO {

    EntradaInventarioDAO entradaInventarioDAO;
    ItemEntradaInventarioDAO itemEntradaInventarioDAO;
    ProductoAlmacenDAO productoAlmacenDAO;

    public List<Object[]> ObtenerEntradaInventarios() {
        List<Object[]> lista;
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.AbrirSesion();
            lista = entradaInventarioDAO.ObtenerEntradaInventarios();
        } catch (Exception e) {
            throw e;
        } finally {
            entradaInventarioDAO.CerrarSesion();
        }
        return lista;
    }

    public EntradaInventario ObtenerEntradaInventario(int idEntradaInventario) {
        EntradaInventario entradaInventario;
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.AbrirSesion();
            entradaInventario = entradaInventarioDAO.ObtenerPorId(EntradaInventario.class, idEntradaInventario);
            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idEntradaInventario", idEntradaInventario});
            List<ItemEntradaInventario> items = itemEntradaInventarioDAO.ObtenerLista(ItemEntradaInventario.class, filtros);
            entradaInventario.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            entradaInventarioDAO.CerrarSesion();
        }
        return entradaInventario;
    }

    public boolean RegistrarEntradaInventario(EntradaInventario entradaInventario) {
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.IniciarTransaccion();
            entradaInventarioDAO.Agregar(entradaInventario);

            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(entradaInventarioDAO);

            for (ItemEntradaInventario item : entradaInventario.getItems()) {
                item.setIdEntradaInventario(entradaInventario.getIdEntradaInventario());
                item.setIdAlmacen(entradaInventario.getIdAlmacen());
                itemEntradaInventarioDAO.Agregar(item);
                productoAlmacenDAO.ActualizarStockFisico(item.getIdProducto(), item.getIdAlmacen(), item.getCantidad() * item.getFactor());
            }

            entradaInventarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            entradaInventarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            entradaInventarioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarEntradaInventario(int idEntradaInventario) {
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.IniciarTransaccion();
            entradaInventarioDAO.EliminarEntradaInventario(idEntradaInventario);

            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idEntradaInventario", idEntradaInventario});
            List<ItemEntradaInventario> items = itemEntradaInventarioDAO.ObtenerLista(ItemEntradaInventario.class, filtros);

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(entradaInventarioDAO);

            for (ItemEntradaInventario item : items) {
                productoAlmacenDAO.ActualizarStockFisico(item.getIdProducto(), item.getIdAlmacen(), item.getCantidad() * item.getFactor() * -1);
            }

            itemEntradaInventarioDAO.EliminarItemEntradaInventarioPorIdEntradaInventario(idEntradaInventario);

            entradaInventarioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            entradaInventarioDAO.AbortarTransaccion();
            throw e;
        } finally {
            entradaInventarioDAO.CerrarSesion();
        }
        return true;
    }
}
