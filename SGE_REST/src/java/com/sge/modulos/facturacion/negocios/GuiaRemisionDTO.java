package com.sge.modulos.facturacion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.facturacion.accesoDatos.GuiaRemisionDAO;
import com.sge.modulos.facturacion.accesoDatos.ItemGuiaRemisionDAO;
import com.sge.modulos.facturacion.entidades.GuiaRemision;
import com.sge.modulos.facturacion.entidades.ItemGuiaRemision;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class GuiaRemisionDTO extends BaseDTO {

    GuiaRemisionDAO guiaRemisionDAO;
    ItemGuiaRemisionDAO itemGuiaRemisionDAO;
    NumeracionDAO numeracionDAO;

    public GuiaRemisionDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<GuiaRemision> ObtenerGuiasRemision(String filtro) {
        List<GuiaRemision> lista;
        try {
            guiaRemisionDAO = new GuiaRemisionDAO();
            guiaRemisionDAO.AbrirSesion();
            lista = guiaRemisionDAO.ObtenerGuiasRemision(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            guiaRemisionDAO.CerrarSesion();
        }
        return lista;
    }

    public GuiaRemision ObtenerGuiaRemision(int idGuiaRemision) {
        GuiaRemision guiaRemision = null;
        try {
            guiaRemisionDAO = new GuiaRemisionDAO();
            guiaRemisionDAO.AbrirSesion();
            guiaRemision = guiaRemisionDAO.ObtenerPorId(GuiaRemision.class, idGuiaRemision);

            itemGuiaRemisionDAO = new ItemGuiaRemisionDAO();
            itemGuiaRemisionDAO.AsignarSesion(guiaRemisionDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idGuiaRemision", idGuiaRemision});
            List<ItemGuiaRemision> items = itemGuiaRemisionDAO.ObtenerLista(ItemGuiaRemision.class, filtros);
            guiaRemision.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            guiaRemisionDAO.CerrarSesion();
        }
        return guiaRemision;
    }

    public boolean RegistrarGuiaRemision(GuiaRemision guiaRemision) {
        try {
            guiaRemisionDAO = new GuiaRemisionDAO();
            guiaRemisionDAO.IniciarTransaccion();

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(guiaRemisionDAO);

            if (!guiaRemision.isNumeracionManual()) {
                guiaRemision.setNumero(numeracionDAO.GenerarNumeracion(guiaRemision.getIdNumeracion()));
            }

            guiaRemisionDAO.Agregar(guiaRemision);

            itemGuiaRemisionDAO = new ItemGuiaRemisionDAO();
            itemGuiaRemisionDAO.AsignarSesion(guiaRemisionDAO);
            for (ItemGuiaRemision item : guiaRemision.getItems()) {
                item.setIdGuiaRemision(guiaRemision.getIdGuiaRemision());
                itemGuiaRemisionDAO.Agregar(item);
            }

            guiaRemisionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            guiaRemisionDAO.AbortarTransaccion();
            throw e;
        } finally {
            guiaRemisionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarGuiaRemision(GuiaRemision guiaRemision) {
        try {
            guiaRemisionDAO = new GuiaRemisionDAO();
            guiaRemisionDAO.IniciarTransaccion();
            guiaRemisionDAO.ActualizarGuiaRemision(guiaRemision);

            itemGuiaRemisionDAO = new ItemGuiaRemisionDAO();
            itemGuiaRemisionDAO.AsignarSesion(guiaRemisionDAO);
            for (ItemGuiaRemision item : guiaRemision.getItems()) {
                if (item.isAgregar()) {
                    item.setIdGuiaRemision(guiaRemision.getIdGuiaRemision());
                    itemGuiaRemisionDAO.Agregar(item);
                }
                if (item.isEliminar()) {
                    itemGuiaRemisionDAO.EliminarItemGuiaRemision(item.getIdItemGuiaRemision());
                }
            }

            guiaRemisionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            guiaRemisionDAO.AbortarTransaccion();
            throw e;
        } finally {
            guiaRemisionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarGuiaRemision(int idGuiaRemision) {
        try {
            guiaRemisionDAO = new GuiaRemisionDAO();
            guiaRemisionDAO.IniciarTransaccion();
            guiaRemisionDAO.EliminarGuiaRemision(idGuiaRemision);

            itemGuiaRemisionDAO = new ItemGuiaRemisionDAO();
            itemGuiaRemisionDAO.AsignarSesion(guiaRemisionDAO);
            itemGuiaRemisionDAO.EliminarItemGuiaRemisionPorGuiaRemision(idGuiaRemision);

            guiaRemisionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            guiaRemisionDAO.AbortarTransaccion();
            throw e;
        } finally {
            guiaRemisionDAO.CerrarSesion();
        }
        return true;
    }
}
