package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.ItemSolicitudCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.SolicitudCotizacionDAO;
import com.sge.modulos.ventas.entidades.ItemSolicitudCotizacion;
import com.sge.modulos.ventas.entidades.SolicitudCotizacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SolicitudCotizacionDTO {
    
    SolicitudCotizacionDAO solicitudCotizacionDAO;
    ItemSolicitudCotizacionDAO itemSolicitudCotizacionDAO;
    
    public List<SolicitudCotizacion> ObtenerSolicitudesCotizacion(String filtro) {
        List<SolicitudCotizacion> lista;
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.AbrirSesion();
            lista = solicitudCotizacionDAO.ObtenerSolicitudesCotizacion(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return lista;
    }

    public SolicitudCotizacion ObtenerSolicitudCotizacion(int idSolicitudCotizacion) {
        SolicitudCotizacion solicitudCotizacion = null;
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.AbrirSesion();
            solicitudCotizacion = solicitudCotizacionDAO.ObtenerPorId(SolicitudCotizacion.class, idSolicitudCotizacion);
            
            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSolicitudCotizacion", idSolicitudCotizacion});
            List<ItemSolicitudCotizacion> items = itemSolicitudCotizacionDAO.ObtenerLista(ItemSolicitudCotizacion.class, filtros);
            solicitudCotizacion.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return solicitudCotizacion;
    }

    public boolean RegistrarSolicitudCotizacion(SolicitudCotizacion solicitudCotizacion) {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            solicitudCotizacion.setEstado("PENDIENTE DE APROBACIÓN");
            solicitudCotizacionDAO.Agregar(solicitudCotizacion);

            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);
            for (ItemSolicitudCotizacion item : solicitudCotizacion.getItems()) {
                item.setIdSolicitudCotizacion(solicitudCotizacion.getIdSolicitudCotizacion());
                itemSolicitudCotizacionDAO.Agregar(item);
            }

            solicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            solicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarSolicitudCotizacion(SolicitudCotizacion solicitudCotizacion) {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            solicitudCotizacionDAO.ActualizarSolicitudCotizacion(solicitudCotizacion);

            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);
            for (ItemSolicitudCotizacion item : solicitudCotizacion.getItems()) {
                if(item.isAgregar()){
                    item.setIdSolicitudCotizacion(solicitudCotizacion.getIdSolicitudCotizacion());
                    itemSolicitudCotizacionDAO.Agregar(item);
                }
                if(item.isActualizar()){
                    itemSolicitudCotizacionDAO.ActualizarItemSolicitudCotizacion(item);
                }
                if(item.isEliminar()){
                    itemSolicitudCotizacionDAO.EliminarItemSolicitudCotizacion(item.getIdItemSolicitudCotizacion());
                }
            }
            solicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            solicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarSolicitudCotizacion(int idSolicitudCotizacion) {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            solicitudCotizacionDAO.EliminarSolicitudCotizacion(idSolicitudCotizacion);

            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);
            itemSolicitudCotizacionDAO.EliminarItemSolicitudCotizacionPorIdSolicitudCotizacion(idSolicitudCotizacion);

            solicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            solicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean AprobarSolicitudCotizacion(int idSolicitudCotizacion) {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(idSolicitudCotizacion, "APROBADO");
            solicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            solicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean DesaprobarSolicitudCotizacion(int idSolicitudCotizacion) {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(idSolicitudCotizacion, "PENDIENTE DE APROBACIÓN");
            solicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            solicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            solicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }
}
