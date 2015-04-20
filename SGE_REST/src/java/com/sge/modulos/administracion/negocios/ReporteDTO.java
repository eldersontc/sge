package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.ItemReporteDAO;
import com.sge.modulos.administracion.accesoDatos.ReporteDAO;
import com.sge.modulos.administracion.entidades.ItemReporte;
import com.sge.modulos.administracion.entidades.Reporte;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author elderson
 */
public class ReporteDTO {

    ReporteDAO reporteDAO;
    ItemReporteDAO itemReporteDAO;

    public List<Object[]> ObtenerReportes(String filtro) {
        List<Object[]> lista;
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.AbrirSesion();
            lista = reporteDAO.ObtenerReportes(filtro);
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
            reporteDAO.AbrirSesion();
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
                item.setIdReporte(reporte.getIdReporte());
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
            reporteDAO.ActualizarReporte(reporte.getIdReporte(), reporte.getNombre(), reporte.getIdEntidad(), reporte.getUbicacion(), reporte.isActivo());
            itemReporteDAO = new ItemReporteDAO();
            itemReporteDAO.AsignarSesion(reporteDAO);
            for (ItemReporte item : reporte.getItems()) {
                if (item.isAgregar()) {
                    item.setIdReporte(reporte.getIdReporte());
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

    //////////////////// GENERACION DE REPORTES ////////////////////////////////
    public byte[] GenerarReporteConEntidad(int idReporte, int id) throws Exception {
        byte[] bytes = null;
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.AbrirSesion();
            Reporte reporte = reporteDAO.ObtenerPorId(Reporte.class, idReporte);
            Map parametros = new HashMap();
            for (ItemReporte item : reporte.getItems()) {
                if (item.isAsignarId()) {
                    parametros.put(item.getNombre(), id);
                } else {
                    parametros.put(item.getNombre(), item.getValor());
                }
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte.getUbicacion(), parametros, reporteDAO.getConexion());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(jasperPrint);
            bytes = out.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return bytes;
    }
    
    public byte[] GenerarReporteSinEntidad(int idReporte) throws Exception {
        byte[] bytes = null;
        try {
            reporteDAO = new ReporteDAO();
            reporteDAO.AbrirSesion();
            Reporte reporte = reporteDAO.ObtenerPorId(Reporte.class, idReporte);
            Map parametros = new HashMap();
            for (ItemReporte item : reporte.getItems()) {
                parametros.put(item.getNombre(), item.getValor());
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte.getUbicacion(), parametros, reporteDAO.getConexion());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(jasperPrint);
            bytes = out.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            reporteDAO.CerrarSesion();
        }
        return bytes;
    }
}
