package com.sge.modulos.finanzas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.finanzas.accesoDatos.CajaDAO;
import com.sge.modulos.finanzas.accesoDatos.SalidaCajaDAO;
import com.sge.modulos.finanzas.accesoDatos.ItemSalidaCajaDAO;
import com.sge.modulos.finanzas.entidades.SalidaCaja;
import com.sge.modulos.finanzas.entidades.ItemSalidaCaja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SalidaCajaDTO extends BaseDTO {

    SalidaCajaDAO salidaCajaDAO;
    ItemSalidaCajaDAO itemSalidaCajaDAO;
    CajaDAO cajaDAO;
    NumeracionDAO numeracionDAO;

    public SalidaCajaDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<SalidaCaja> ObtenerSalidasCaja(String filtro) {
        List<SalidaCaja> lista;
        try {
            salidaCajaDAO = new SalidaCajaDAO();
            salidaCajaDAO.AbrirSesion();
            lista = salidaCajaDAO.ObtenerSalidasCaja(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            salidaCajaDAO.CerrarSesion();
        }
        return lista;
    }

    public SalidaCaja ObtenerSalidaCaja(int idSalidaCaja) {
        SalidaCaja salidaCaja;
        try {
            salidaCajaDAO = new SalidaCajaDAO();
            salidaCajaDAO.AbrirSesion();
            salidaCaja = salidaCajaDAO.ObtenerPorId(SalidaCaja.class, idSalidaCaja);
            itemSalidaCajaDAO = new ItemSalidaCajaDAO();
            itemSalidaCajaDAO.AsignarSesion(salidaCajaDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSalidaCaja", idSalidaCaja});
            List<ItemSalidaCaja> items = itemSalidaCajaDAO.ObtenerLista(ItemSalidaCaja.class, filtros);
            salidaCaja.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            salidaCajaDAO.CerrarSesion();
        }
        return salidaCaja;
    }

    public boolean RegistrarSalidaCaja(SalidaCaja salidaCaja) {
        try {
            salidaCajaDAO = new SalidaCajaDAO();
            salidaCajaDAO.IniciarTransaccion();

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(salidaCajaDAO);

            if (!salidaCaja.isNumeracionManual()) {
                salidaCaja.setNumero(numeracionDAO.GenerarNumeracion(salidaCaja.getIdNumeracion()));
            }

            salidaCajaDAO.Agregar(salidaCaja);

            itemSalidaCajaDAO = new ItemSalidaCajaDAO();
            itemSalidaCajaDAO.AsignarSesion(salidaCajaDAO);

            cajaDAO = new CajaDAO();
            cajaDAO.AsignarSesion(salidaCajaDAO);

            for (ItemSalidaCaja item : salidaCaja.getItems()) {
                item.setIdSalidaCaja(salidaCaja.getIdSalidaCaja());
                itemSalidaCajaDAO.Agregar(item);
            }

            cajaDAO.ActualizarSaldo(salidaCaja.getIdCaja(), salidaCaja.getTotal() * -1);

            salidaCajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            salidaCajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            salidaCajaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarSalidaCaja(int idSalidaCaja) {
        try {
            salidaCajaDAO = new SalidaCajaDAO();
            salidaCajaDAO.IniciarTransaccion();
            salidaCajaDAO.EliminarSalidaCaja(idSalidaCaja);

            itemSalidaCajaDAO = new ItemSalidaCajaDAO();
            itemSalidaCajaDAO.AsignarSesion(salidaCajaDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSalidaCaja", idSalidaCaja});
            List<ItemSalidaCaja> items = itemSalidaCajaDAO.ObtenerLista(ItemSalidaCaja.class, filtros);

            cajaDAO = new CajaDAO();
            cajaDAO.AsignarSesion(salidaCajaDAO);

            for (ItemSalidaCaja item : items) {
                //
            }

            itemSalidaCajaDAO.EliminarItemSalidaCajaPorSalidaCaja(idSalidaCaja);

            salidaCajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            salidaCajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            salidaCajaDAO.CerrarSesion();
        }
        return true;
    }
}
