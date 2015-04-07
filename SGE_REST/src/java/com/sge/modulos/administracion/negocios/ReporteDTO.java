package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.ItemReporteDAO;
import com.sge.modulos.administracion.accesoDatos.ReporteDAO;
import com.sge.modulos.administracion.entidades.ItemReporte;
import com.sge.modulos.administracion.entidades.Reporte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ReporteDTO {

    ReporteDAO reporteDAO;
    ItemReporteDAO itemReporteDAO;

    public List<Object[]> ObtenerReportes() {
        List<Object[]> lista;
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.AbrirSesision();
            lista = reporteDAO.ObtenerReportes();
        } catch (Exception e) {
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return lista;
    }

    public Reporte ObtenerReporte(int idReporte) {
        Reporte reporte;
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.AbrirSesision();
            reporte = reporteDAO.ObtenerPorId(Reporte.class, idReporte);
            itemReporteDAO = new ItemReporteDAO();
            itemReporteDAO.AsignarSesion(reporteDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idReporte", idReporte});
            List<ItemReporte> items = itemReporteDAO.ObtenerLista(ItemReporte.class, filtros);
            reporte.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return reporte;
    }

    public boolean RegistrarReporte(Reporte reporte) {
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.IniciarTransaccion();
            reporteDAO.Agregar(reporte);
            itemReporteDAO = new ItemReporteDAO();
            itemReporteDAO.AsignarSesion(reporteDAO);
            for (ItemReporte item : reporte.getItems()) {
                item.setIdItemReporte(reporte.getIdReporte());
                itemReporteDAO.Agregar(item);
            }
            reporteDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            reporteDAO.AbortarTransaccion();
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarReporte(Reporte reporte) {
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.IniciarTransaccion();
            reporteDAO.ActualizarReporte(reporte.getIdReporte(), reporte.getNombre(), reporte.getEntidad(), reporte.getUbicacion(), reporte.isActivo());
            itemReporteDAO = new ItemReporteDAO();
            itemReporteDAO.AsignarSesion(reporteDAO);
            for (ItemReporte item : reporte.getItems()) {
                if (item.isAgregar()) {
                    item.setIdItemReporte(reporte.getIdReporte());
                    itemReporteDAO.Agregar(item);
                }
                if (item.isActualizar()) {
                    itemReporteDAO.ActualizarItemReporte(item.getIdItemReporte(), item.getNombre(), item.isAsignarId(), item.getValor());
                }
                if (item.isEliminar()) {
                    itemReporteDAO.EliminarItemReporte(item.getIdItemReporte());
                }
            }
            reporteDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            reporteDAO.AbortarTransaccion();
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarReporte(int idReporte) {
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.IniciarTransaccion();
            reporteDAO.EliminarReporte(idReporte);
            itemReporteDAO = new ItemReporteDAO();
            itemReporteDAO.AsignarSesion(reporteDAO);
            itemReporteDAO.EliminarItemReportePorIdReporte(idReporte);
            reporteDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            reporteDAO.AbortarTransaccion();
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return true;
    }
}
