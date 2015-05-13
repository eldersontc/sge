package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.ItemPresupuestoDAO;
import com.sge.modulos.ventas.accesoDatos.PresupuestoDAO;
import com.sge.modulos.ventas.entidades.ItemPresupuesto;
import com.sge.modulos.ventas.entidades.Presupuesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PresupuestoDTO {

    PresupuestoDAO presupuestoDAO;
    ItemPresupuestoDAO itemPresupuestoDAO;

    public List<Object[]> ObtenerPresupuestos(String filtro) {
        List<Object[]> lista;
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.AbrirSesion();
            lista = presupuestoDAO.ObtenerPresupuestos(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return lista;
    }

    public Presupuesto ObtenerPresupuesto(int idPresupuesto) {
        Presupuesto presupuesto = null;
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.AbrirSesion();
            presupuesto = presupuestoDAO.ObtenerPorId(Presupuesto.class, idPresupuesto);

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idPresupuesto", idPresupuesto});
            List<ItemPresupuesto> items = itemPresupuestoDAO.ObtenerLista(ItemPresupuesto.class, filtros);
            presupuesto.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return presupuesto;
    }

    public boolean RegistrarPresupuesto(Presupuesto presupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.Agregar(presupuesto);

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);
            for (ItemPresupuesto item : presupuesto.getItems()) {
                item.setIdPresupuesto(presupuesto.getIdPresupuesto());
                itemPresupuestoDAO.Agregar(item);
            }

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarPresupuesto(Presupuesto presupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.ActualizarPresupuesto(presupuesto.getIdPresupuesto(), presupuesto.getIdCliente(), presupuesto.getRazonSocialCliente(), presupuesto.getIdMoneda(), presupuesto.getSimboloMoneda(), presupuesto.getNumeroOrdenCompra(), presupuesto.getInstrucciones(), presupuesto.getTotal());

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);
            for (ItemPresupuesto item : presupuesto.getItems()) {
                if (item.isAgregar()) {
                    item.setIdPresupuesto(presupuesto.getIdPresupuesto());
                    itemPresupuestoDAO.Agregar(item);
                }
                if (item.isActualizar()) {
                    itemPresupuestoDAO.ActualizarItemPresupuesto(item.getIdPresupuesto(), item.getRecargo(), item.getTotal());
                }
                if (item.isEliminar()) {
                    itemPresupuestoDAO.EliminarItemPresupuesto(item.getIdItemPresupuesto());
                }
            }

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarPresupuesto(int idPresupuesto) {
        try {
            presupuestoDAO = new PresupuestoDAO();
            presupuestoDAO.IniciarTransaccion();
            presupuestoDAO.EliminarPresupuesto(idPresupuesto);

            itemPresupuestoDAO = new ItemPresupuestoDAO();
            itemPresupuestoDAO.AsignarSesion(presupuestoDAO);
            itemPresupuestoDAO.EliminarItemPresupuestoPorPresupuesto(idPresupuesto);

            presupuestoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            presupuestoDAO.AbortarTransaccion();
            throw e;
        } finally {
            presupuestoDAO.CerrarSesion();
        }
        return true;
    }
}
