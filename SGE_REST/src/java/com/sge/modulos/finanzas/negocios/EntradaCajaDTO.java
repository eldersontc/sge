package com.sge.modulos.finanzas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.finanzas.accesoDatos.CajaDAO;
import com.sge.modulos.finanzas.accesoDatos.EntradaCajaDAO;
import com.sge.modulos.finanzas.accesoDatos.ItemEntradaCajaDAO;
import com.sge.modulos.finanzas.entidades.EntradaCaja;
import com.sge.modulos.finanzas.entidades.ItemEntradaCaja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaCajaDTO extends BaseDTO {

    EntradaCajaDAO entradaCajaDAO;
    ItemEntradaCajaDAO itemEntradaCajaDAO;
    CajaDAO cajaDAO;
    NumeracionDAO numeracionDAO;

    public EntradaCajaDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<EntradaCaja> ObtenerEntradasCaja(String filtro) {
        List<EntradaCaja> lista;
        try {
            entradaCajaDAO = new EntradaCajaDAO();
            entradaCajaDAO.AbrirSesion();
            lista = entradaCajaDAO.ObtenerEntradasCaja(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            entradaCajaDAO.CerrarSesion();
        }
        return lista;
    }

    public EntradaCaja ObtenerEntradaCaja(int idEntradaCaja) {
        EntradaCaja entradaCaja;
        try {
            entradaCajaDAO = new EntradaCajaDAO();
            entradaCajaDAO.AbrirSesion();
            entradaCaja = entradaCajaDAO.ObtenerPorId(EntradaCaja.class, idEntradaCaja);
            itemEntradaCajaDAO = new ItemEntradaCajaDAO();
            itemEntradaCajaDAO.AsignarSesion(entradaCajaDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idEntradaCaja", idEntradaCaja});
            List<ItemEntradaCaja> items = itemEntradaCajaDAO.ObtenerLista(ItemEntradaCaja.class, filtros);
            entradaCaja.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            entradaCajaDAO.CerrarSesion();
        }
        return entradaCaja;
    }

    public boolean RegistrarEntradaCaja(EntradaCaja entradaCaja) {
        try {
            entradaCajaDAO = new EntradaCajaDAO();
            entradaCajaDAO.IniciarTransaccion();

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(entradaCajaDAO);

            if (!entradaCaja.isNumeracionManual()) {
                entradaCaja.setNumero(numeracionDAO.GenerarNumeracion(entradaCaja.getIdNumeracion()));
            }

            entradaCajaDAO.Agregar(entradaCaja);

            itemEntradaCajaDAO = new ItemEntradaCajaDAO();
            itemEntradaCajaDAO.AsignarSesion(entradaCajaDAO);

            cajaDAO = new CajaDAO();
            cajaDAO.AsignarSesion(entradaCajaDAO);

            for (ItemEntradaCaja item : entradaCaja.getItems()) {
                item.setIdEntradaCaja(entradaCaja.getIdEntradaCaja());
                itemEntradaCajaDAO.Agregar(item);
            }

            cajaDAO.ActualizarSaldo(entradaCaja.getIdCaja(), entradaCaja.getTotal());

            entradaCajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            entradaCajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            entradaCajaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarEntradaCaja(int idEntradaCaja) {
        try {
            entradaCajaDAO = new EntradaCajaDAO();
            entradaCajaDAO.IniciarTransaccion();
            entradaCajaDAO.EliminarEntradaCaja(idEntradaCaja);

            itemEntradaCajaDAO = new ItemEntradaCajaDAO();
            itemEntradaCajaDAO.AsignarSesion(entradaCajaDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idEntradaCaja", idEntradaCaja});
            List<ItemEntradaCaja> items = itemEntradaCajaDAO.ObtenerLista(ItemEntradaCaja.class, filtros);

            cajaDAO = new CajaDAO();
            cajaDAO.AsignarSesion(entradaCajaDAO);

            for (ItemEntradaCaja item : items) {
                //
            }

            itemEntradaCajaDAO.EliminarItemEntradaCajaPorEntradaCaja(idEntradaCaja);

            entradaCajaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            entradaCajaDAO.AbortarTransaccion();
            throw e;
        } finally {
            entradaCajaDAO.CerrarSesion();
        }
        return true;
    }
}
