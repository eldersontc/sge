package com.sge.modulos.inventarios.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
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
public class EntradaInventarioDTO extends BaseDTO {

    EntradaInventarioDAO entradaInventarioDAO;
    ItemEntradaInventarioDAO itemEntradaInventarioDAO;
    ProductoAlmacenDAO productoAlmacenDAO;
    NumeracionDAO numeracionDAO;

    public EntradaInventarioDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<EntradaInventario> ObtenerEntradaInventarios(String filtro) {
        List<EntradaInventario> lista;
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.AbrirSesion();
            lista = entradaInventarioDAO.ObtenerEntradaInventarios(filtro);
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

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(entradaInventarioDAO);

            if (!entradaInventario.isNumeracionManual()) {
                entradaInventario.setNumero(numeracionDAO.GenerarNumeracion(entradaInventario.getIdNumeracion()));
            }

            entradaInventarioDAO.Agregar(entradaInventario);

            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);

            for (ItemEntradaInventario item : entradaInventario.getItems()) {
                item.setIdEntradaInventario(entradaInventario.getIdEntradaInventario());
                item.setIdAlmacen(entradaInventario.getIdAlmacen());
                itemEntradaInventarioDAO.Agregar(item);
            }

            entradaInventarioDAO.PreconfirmarTransaccion();

            productoAlmacenDAO = new ProductoAlmacenDAO();
            productoAlmacenDAO.AsignarSesion(entradaInventarioDAO);

            for (ItemEntradaInventario item : entradaInventario.getItems()) {
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
