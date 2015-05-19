package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.CotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.ItemCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.SolicitudCotizacionDAO;
import com.sge.modulos.ventas.entidades.Cotizacion;
import com.sge.modulos.ventas.entidades.ItemCotizacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class CotizacionDTO {
    
    CotizacionDAO cotizacionDAO;
    ItemCotizacionDAO itemCotizacionDAO;
    SolicitudCotizacionDAO solicitudCotizacionDAO;
    
    public List<Object[]> ObtenerCotizaciones(String filtro) {
        List<Object[]> lista;
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AbrirSesion();
            lista = cotizacionDAO.ObtenerCotizaciones(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return lista;
    }

    public List<Cotizacion> ObtenerCotizacionesPorPresupuesto(int idPresupuesto) {
        List<Cotizacion> lista;
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AbrirSesion();
            lista = cotizacionDAO.ObtenerCotizacionesPorPresupuesto(idPresupuesto);
            
            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            for (Cotizacion cotizacion : lista) {
                List<Object[]> filtros = new ArrayList<>();
                filtros.add(new Object[]{"idCotizacion", cotizacion.getIdCotizacion()});
                List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);
                cotizacion.setItems(items);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return lista;
    }
    
    public Cotizacion ObtenerCotizacion(int idCotizacion) {
        Cotizacion cotizacion = null;
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AbrirSesion();
            cotizacion = cotizacionDAO.ObtenerPorId(Cotizacion.class, idCotizacion);
            
            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idCotizacion", idCotizacion});
            List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);
            cotizacion.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return cotizacion;
    }

    public boolean RegistrarCotizacion(Cotizacion cotizacion) {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            cotizacionDAO.Agregar(cotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            for (ItemCotizacion item : cotizacion.getItems()) {
                item.setIdCotizacion(cotizacion.getIdCotizacion());
                itemCotizacionDAO.Agregar(item);
            }
            
            if(cotizacion.getIdSolicitudCotizacion() > 0){
                solicitudCotizacionDAO = new SolicitudCotizacionDAO();
                solicitudCotizacionDAO.AsignarSesion(cotizacionDAO);
                solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(cotizacion.getIdSolicitudCotizacion(), "COTIZACIÓN GENERADA");
            }
            
            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarCotizacion(Cotizacion cotizacion) {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            cotizacionDAO.ActualizarCotizacion(cotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            for (ItemCotizacion item : cotizacion.getItems()) {
                if(item.isAgregar()){
                    item.setIdCotizacion(cotizacion.getIdCotizacion());
                    itemCotizacionDAO.Agregar(item);
                }
                if(item.isActualizar()){
                    itemCotizacionDAO.ActualizarItemCotizacion(item);
                }
                if(item.isEliminar()){
                    itemCotizacionDAO.EliminarItemCotizacion(item.getIdItemCotizacion());
                }
            }

            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarCotizacion(int idCotizacion) {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            
            Cotizacion cotizacion = cotizacionDAO.ObtenerPorId(Cotizacion.class, idCotizacion);
            if(cotizacion.getIdSolicitudCotizacion() > 0){
                solicitudCotizacionDAO = new SolicitudCotizacionDAO();
                solicitudCotizacionDAO.AsignarSesion(cotizacionDAO);
                solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(cotizacion.getIdSolicitudCotizacion(), "APROBADO");
            }
            
            cotizacionDAO.EliminarCotizacion(idCotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            itemCotizacionDAO.EliminarItemCotizacionPorIdCotizacion(idCotizacion);

            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }
}
