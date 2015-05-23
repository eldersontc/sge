package com.sge.modulos.produccion.negocios;

import com.sge.modulos.produccion.accesoDatos.ItemOrdenProduccionDAO;
import com.sge.modulos.produccion.accesoDatos.OrdenProduccionDAO;
import com.sge.modulos.produccion.entidades.ItemOrdenProduccion;
import com.sge.modulos.produccion.entidades.OrdenProduccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class OrdenProduccionDTO {
    
    OrdenProduccionDAO ordenProduccionDAO;
    ItemOrdenProduccionDAO itemOrdenProduccionDAO;

    public List<OrdenProduccion> ObtenerOrdenesProduccion(String filtro) {
        List<OrdenProduccion> lista;
        try {
            ordenProduccionDAO = new OrdenProduccionDAO();
            ordenProduccionDAO.AbrirSesion();
            lista = ordenProduccionDAO.ObtenerOrdenesProduccion(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            ordenProduccionDAO.CerrarSesion();
        }
        return lista;
    }

    public OrdenProduccion ObtenerOrdenProduccion(int idOrdenProduccion) {
        OrdenProduccion ordenProduccion = null;
        try {
            ordenProduccionDAO = new OrdenProduccionDAO();
            ordenProduccionDAO.AbrirSesion();
            ordenProduccion = ordenProduccionDAO.ObtenerPorId(OrdenProduccion.class, idOrdenProduccion);

            itemOrdenProduccionDAO = new ItemOrdenProduccionDAO();
            itemOrdenProduccionDAO.AsignarSesion(ordenProduccionDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idOrdenProduccion", idOrdenProduccion});
            List<ItemOrdenProduccion> items = itemOrdenProduccionDAO.ObtenerLista(ItemOrdenProduccion.class, filtros);
            ordenProduccion.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            ordenProduccionDAO.CerrarSesion();
        }
        return ordenProduccion;
    }

    public boolean RegistrarOrdenProduccion(OrdenProduccion ordenProduccion) {
        try {
            ordenProduccionDAO = new OrdenProduccionDAO();
            ordenProduccionDAO.IniciarTransaccion();
            ordenProduccionDAO.Agregar(ordenProduccion);

            itemOrdenProduccionDAO = new ItemOrdenProduccionDAO();
            itemOrdenProduccionDAO.AsignarSesion(ordenProduccionDAO);
            for (ItemOrdenProduccion item : ordenProduccion.getItems()) {
                item.setIdOrdenProduccion(ordenProduccion.getIdOrdenProduccion());
                itemOrdenProduccionDAO.Agregar(item);
            }

            ordenProduccionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            ordenProduccionDAO.AbortarTransaccion();
            throw e;
        } finally {
            ordenProduccionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarOrdenProduccion(OrdenProduccion ordenProduccion) {
        try {
            ordenProduccionDAO = new OrdenProduccionDAO();
            ordenProduccionDAO.IniciarTransaccion();
            ordenProduccionDAO.ActualizarOrdenProduccion(ordenProduccion.getIdOrdenProduccion(), ordenProduccion.getIdCliente(), ordenProduccion.getRazonSocialCliente(), ordenProduccion.getIdResponsable(), ordenProduccion.getNombreResponsable());

            itemOrdenProduccionDAO = new ItemOrdenProduccionDAO();
            itemOrdenProduccionDAO.AsignarSesion(ordenProduccionDAO);
            for (ItemOrdenProduccion item : ordenProduccion.getItems()) {
                if (item.isAgregar()) {
                    item.setIdOrdenProduccion(ordenProduccion.getIdOrdenProduccion());
                    itemOrdenProduccionDAO.Agregar(item);
                }
                if (item.isEliminar()) {
                    itemOrdenProduccionDAO.EliminarItemOrdenProduccion(item.getIdItemOrdenProduccion());
                }
            }

            ordenProduccionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            ordenProduccionDAO.AbortarTransaccion();
            throw e;
        } finally {
            ordenProduccionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarOrdenProduccion(int idOrdenProduccion) {
        try {
            ordenProduccionDAO = new OrdenProduccionDAO();
            ordenProduccionDAO.IniciarTransaccion();
            ordenProduccionDAO.EliminarOrdenProduccion(idOrdenProduccion);

            itemOrdenProduccionDAO = new ItemOrdenProduccionDAO();
            itemOrdenProduccionDAO.AsignarSesion(ordenProduccionDAO);
            itemOrdenProduccionDAO.EliminarItemOrdenProduccionPorOrdenProduccion(idOrdenProduccion);

            ordenProduccionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            ordenProduccionDAO.AbortarTransaccion();
            throw e;
        } finally {
            ordenProduccionDAO.CerrarSesion();
        }
        return true;
    }
}
