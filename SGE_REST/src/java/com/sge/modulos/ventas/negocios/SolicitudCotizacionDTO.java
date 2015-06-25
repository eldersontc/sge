package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.ventas.accesoDatos.GrupoSolicitudCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.ItemSolicitudCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.SolicitudCotizacionDAO;
import com.sge.modulos.ventas.entidades.GrupoSolicitudCotizacion;
import com.sge.modulos.ventas.entidades.ItemSolicitudCotizacion;
import com.sge.modulos.ventas.entidades.SolicitudCotizacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SolicitudCotizacionDTO extends BaseDTO {

    SolicitudCotizacionDAO solicitudCotizacionDAO;
    GrupoSolicitudCotizacionDAO grupoSolicitudCotizacionDAO;
    ItemSolicitudCotizacionDAO itemSolicitudCotizacionDAO;
    NumeracionDAO numeracionDAO;

    public SolicitudCotizacionDTO(int idUsuario) {
        super(idUsuario);
    }

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

    public SolicitudCotizacion ObtenerSolicitudCotizacion(int idSolicitudCotizacion, boolean validarEstado) throws Exception {
        SolicitudCotizacion solicitudCotizacion = null;
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.AbrirSesion();
            solicitudCotizacion = solicitudCotizacionDAO.ObtenerPorId(SolicitudCotizacion.class, idSolicitudCotizacion);

            if(validarEstado && !solicitudCotizacion.getEstado().equals("APROBADO")){
                throw new Exception("SÓLO SE PUEDE GENERAR COTIZACIÓN CUANDO ESTÁ EN ESTADO : APROBADO.");
            }
            
            grupoSolicitudCotizacionDAO = new GrupoSolicitudCotizacionDAO();
            grupoSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);

            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSolicitudCotizacion", idSolicitudCotizacion});
            List<GrupoSolicitudCotizacion> grupos = grupoSolicitudCotizacionDAO.ObtenerLista(GrupoSolicitudCotizacion.class, filtros);

            for (GrupoSolicitudCotizacion grupo : grupos) {

                filtros = new ArrayList<>();
                filtros.add(new Object[]{"idGrupoSolicitudCotizacion", grupo.getIdGrupoSolicitudCotizacion()});
                List<ItemSolicitudCotizacion> items = itemSolicitudCotizacionDAO.ObtenerLista(ItemSolicitudCotizacion.class, filtros);

                grupo.setItems(items);
            }

            solicitudCotizacion.setGrupos(grupos);
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

            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(solicitudCotizacionDAO);

            if (!solicitudCotizacion.isNumeracionManual()) {
                solicitudCotizacion.setNumero(numeracionDAO.GenerarNumeracion(solicitudCotizacion.getIdNumeracion()));
            }

            solicitudCotizacion.setEstado("PENDIENTE DE APROBACIÓN");

            solicitudCotizacionDAO.Agregar(solicitudCotizacion);

            grupoSolicitudCotizacionDAO = new GrupoSolicitudCotizacionDAO();
            grupoSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);

            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);

            for (GrupoSolicitudCotizacion grupo : solicitudCotizacion.getGrupos()) {

                grupo.setIdSolicitudCotizacion(solicitudCotizacion.getIdSolicitudCotizacion());
                grupoSolicitudCotizacionDAO.Agregar(grupo);

                for (ItemSolicitudCotizacion item : grupo.getItems()) {

                    item.setIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                    itemSolicitudCotizacionDAO.Agregar(item);
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

    public boolean ActualizarSolicitudCotizacion(SolicitudCotizacion solicitudCotizacion) {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            solicitudCotizacionDAO.ActualizarSolicitudCotizacion(solicitudCotizacion);

            grupoSolicitudCotizacionDAO = new GrupoSolicitudCotizacionDAO();
            grupoSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);

            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);

            for (GrupoSolicitudCotizacion grupo : solicitudCotizacion.getGrupos()) {
                if (grupo.isAgregar()) {
                    grupo.setIdSolicitudCotizacion(solicitudCotizacion.getIdSolicitudCotizacion());
                    grupoSolicitudCotizacionDAO.Agregar(grupo);
                    for (ItemSolicitudCotizacion item : grupo.getItems()) {
                        item.setIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                        itemSolicitudCotizacionDAO.Agregar(item);
                    }
                }
                if (grupo.isActualizar()) {
                    grupoSolicitudCotizacionDAO.ActualizarGrupoSolicitudCotizacion(grupo);
                    for (ItemSolicitudCotizacion item : grupo.getItems()) {
                        if(item.isAgregar()){
                            item.setIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                            itemSolicitudCotizacionDAO.Agregar(item);
                        }
                        if(item.isActualizar()){
                            item.setIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                            itemSolicitudCotizacionDAO.ActualizarItemSolicitudCotizacion(item);
                        }
                        if(item.isEliminar()){
                            item.setIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                            itemSolicitudCotizacionDAO.EliminarItemSolicitudCotizacion(item.getIdItemSolicitudCotizacion());
                        }
                    }
                }
                if (grupo.isEliminar()) {
                    grupoSolicitudCotizacionDAO.EliminarGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                    itemSolicitudCotizacionDAO.EliminarItemSolicitudCotizacionPorIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
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

            grupoSolicitudCotizacionDAO = new GrupoSolicitudCotizacionDAO();
            grupoSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);
            
            itemSolicitudCotizacionDAO = new ItemSolicitudCotizacionDAO();
            itemSolicitudCotizacionDAO.AsignarSesion(solicitudCotizacionDAO);
            
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idSolicitudCotizacion", idSolicitudCotizacion});
            List<GrupoSolicitudCotizacion> grupos = grupoSolicitudCotizacionDAO.ObtenerLista(GrupoSolicitudCotizacion.class, filtros);

            for (GrupoSolicitudCotizacion grupo : grupos) {
                grupoSolicitudCotizacionDAO.EliminarGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
                itemSolicitudCotizacionDAO.EliminarItemSolicitudCotizacionPorIdGrupoSolicitudCotizacion(grupo.getIdGrupoSolicitudCotizacion());
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

    public boolean AprobarSolicitudCotizacion(int idSolicitudCotizacion) throws Exception {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            SolicitudCotizacion solicitudCotizacion = solicitudCotizacionDAO.ObtenerPorId(SolicitudCotizacion.class, idSolicitudCotizacion);
            if(solicitudCotizacion.getEstado().equals("PENDIENTE DE APROBACIÓN")){
                solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(idSolicitudCotizacion, "APROBADO");
            } else {
                throw  new Exception("SÓLO SE PUEDE APROBAR CUANDO ESTÁ EN ESTADO : PENDIENTE DE APROBACIÓN.");
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

    public boolean DesaprobarSolicitudCotizacion(int idSolicitudCotizacion) throws Exception {
        try {
            solicitudCotizacionDAO = new SolicitudCotizacionDAO();
            solicitudCotizacionDAO.IniciarTransaccion();
            SolicitudCotizacion solicitudCotizacion = solicitudCotizacionDAO.ObtenerPorId(SolicitudCotizacion.class, idSolicitudCotizacion);
            if(solicitudCotizacion.getEstado().equals("APROBADO")){
                solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(idSolicitudCotizacion, "PENDIENTE DE APROBACIÓN");
            } else {
                throw  new Exception("SÓLO SE PUEDE DESAPROBAR CUANDO ESTÁ EN ESTADO : APROBADO.");
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
}
