package com.sge.modulos.inventarios.negocios;

import com.sge.modulos.inventarios.accesoDatos.EntradaInventarioDAO;
import com.sge.modulos.inventarios.accesoDatos.ItemEntradaInventarioDAO;
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

    public List<Object[]> ObtenerEntradaInventarios() {
        List<Object[]> lista;
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.AbrirSesision();
            lista = entradaInventarioDAO.ObtenerEntradaInventarios();
        } catch (Exception e) {
            throw e;
        } finally {
            entradaInventarioDAO.CerrarSesion();
        }
        return lista;
    }

    public List<Object> ObtenerEntradaInventario(int idEntradaInventario) {
        List<Object> lista = new ArrayList<>();
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.AbrirSesision();
            EntradaInventario entradaInventario = entradaInventarioDAO.ObtenerPorId(EntradaInventario.class, idEntradaInventario);
            lista.add(entradaInventario);
            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idEntradaInventario", idEntradaInventario});
            List<ItemEntradaInventario> items = itemEntradaInventarioDAO.ObtenerLista(ItemEntradaInventario.class, filtros);
            lista.add(items);
        } catch (Exception e) {
            throw e;
        } finally {
            entradaInventarioDAO.CerrarSesion();
        }
        return lista;
    }
    
    public boolean RegistrarEntradaInventario(EntradaInventario entradaInventario, ItemEntradaInventario[] items) {
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.IniciarTransaccion();
            entradaInventarioDAO.Agregar(entradaInventario);

            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);
            for (ItemEntradaInventario item : items) {
                item.setIdEntradaInventario(entradaInventario.getIdEntradaInventario());
                itemEntradaInventarioDAO.Agregar(item);
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
    
    public boolean EliminarProducto(int idEntradaInventario) {
        try {
            entradaInventarioDAO = new EntradaInventarioDAO();
            entradaInventarioDAO.IniciarTransaccion();
            entradaInventarioDAO.EliminarEntradaInventario(idEntradaInventario);

            itemEntradaInventarioDAO = new ItemEntradaInventarioDAO();
            itemEntradaInventarioDAO.AsignarSesion(entradaInventarioDAO);
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
