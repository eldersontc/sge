package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.ServicioDAO;
import com.sge.modulos.ventas.accesoDatos.ServicioMaquinaDAO;
import com.sge.modulos.ventas.accesoDatos.ServicioUnidadDAO;
import com.sge.modulos.ventas.entidades.Servicio;
import com.sge.modulos.ventas.entidades.ServicioMaquina;
import com.sge.modulos.ventas.entidades.ServicioUnidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ServicioDTO {
    
    ServicioDAO servicioDAO;
    ServicioUnidadDAO servicioUnidadDAO;
    ServicioMaquinaDAO servicioMaquinaDAO;

    public List<Object[]> ObtenerServicios(String filtro) {
        List<Object[]> lista;
        try {
            servicioDAO = new ServicioDAO();
            servicioDAO.AbrirSesion();
            lista = servicioDAO.ObtenerServicios(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            servicioDAO.CerrarSesion();
        }
        return lista;
    }

    public Servicio ObtenerServicio(int idServicio) {
        Servicio servicio = null;
        try {
            servicioDAO = new ServicioDAO();
            servicioDAO.AbrirSesion();
            servicio = servicioDAO.ObtenerPorId(Servicio.class, idServicio);
            
            servicioMaquinaDAO = new ServicioMaquinaDAO();
            servicioMaquinaDAO.AsignarSesion(servicioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idServicio", idServicio});
            List<ServicioMaquina> maquinas = servicioMaquinaDAO.ObtenerLista(ServicioMaquina.class, filtros);
            servicio.setMaquinas(maquinas);
            
            servicioUnidadDAO = new ServicioUnidadDAO();
            servicioUnidadDAO.AsignarSesion(servicioDAO);
            List<ServicioUnidad> unidades = servicioUnidadDAO.ObtenerLista(ServicioUnidad.class, filtros);
            servicio.setUnidades(unidades);
            
        } catch (Exception e) {
            throw e;
        } finally {
            servicioDAO.CerrarSesion();
        }
        return servicio;
    }

    public boolean RegistrarServicio(Servicio servicio) {
        try {
            servicioDAO = new ServicioDAO();
            servicioDAO.IniciarTransaccion();
            servicioDAO.Agregar(servicio);

            servicioMaquinaDAO = new ServicioMaquinaDAO();
            servicioMaquinaDAO.AsignarSesion(servicioDAO);
            for (ServicioMaquina servicioMaquina : servicio.getMaquinas()) {
                servicioMaquina.setIdServicio(servicio.getIdServicio());
                servicioMaquinaDAO.Agregar(servicioMaquina);
            }

            servicioUnidadDAO = new ServicioUnidadDAO();
            servicioUnidadDAO.AsignarSesion(servicioDAO);
            for (ServicioUnidad servicioUnidad : servicio.getUnidades()) {
                servicioUnidad.setIdServicio(servicio.getIdServicio());
                servicioUnidadDAO.Agregar(servicioUnidad);
            }

            servicioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            servicioDAO.AbortarTransaccion();
            throw e;
        } finally {
            servicioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarServicio(Servicio servicio) {
        try {
            servicioDAO = new ServicioDAO();
            servicioDAO.IniciarTransaccion();
            servicioDAO.ActualizarServicio(servicio.getIdServicio(), servicio.getCodigo(), servicio.getDescripcion(), servicio.isServicioImpresion(), servicio.isActivo());

            servicioMaquinaDAO = new ServicioMaquinaDAO();
            servicioMaquinaDAO.AsignarSesion(servicioDAO);
            for (ServicioMaquina servicioMaquina : servicio.getMaquinas()) {
                if(servicioMaquina.isAgregar()){
                    servicioMaquina.setIdServicio(servicio.getIdServicio());
                    servicioMaquinaDAO.Agregar(servicioMaquina);
                }
                if(servicioMaquina.isEliminar()){
                    servicioMaquinaDAO.EliminarServicioMaquina(servicioMaquina.getIdServicioMaquina());
                }
            }

            servicioUnidadDAO = new ServicioUnidadDAO();
            servicioUnidadDAO.AsignarSesion(servicioDAO);
            for (ServicioUnidad servicioUnidad : servicio.getUnidades()) {
                if(servicioUnidad.isAgregar()){
                    servicioUnidad.setIdServicio(servicio.getIdServicio());
                    servicioUnidadDAO.Agregar(servicioUnidad);
                }
                if(servicioUnidad.isActualizar()){
                    servicioUnidadDAO.ActualizarServicioUnidad(servicioUnidad.getIdServicioUnidad(), servicioUnidad.getFactor());
                }
                if(servicioUnidad.isEliminar()){
                    servicioUnidadDAO.EliminarServicioUnidad(servicioUnidad.getIdServicioUnidad());
                }
            }

            servicioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            servicioDAO.AbortarTransaccion();
            throw e;
        } finally {
            servicioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarServicio(int idServicio) {
        try {
            servicioDAO = new ServicioDAO();
            servicioDAO.IniciarTransaccion();
            servicioDAO.EliminarServicio(idServicio);

            servicioMaquinaDAO = new ServicioMaquinaDAO();
            servicioMaquinaDAO.AsignarSesion(servicioDAO);
            servicioMaquinaDAO.EliminarServicioMaquinaPorIdServicio(idServicio);

            servicioUnidadDAO = new ServicioUnidadDAO();
            servicioUnidadDAO.AsignarSesion(servicioDAO);
            servicioUnidadDAO.EliminarServicioUnidadPorIdServicio(idServicio);

            servicioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            servicioDAO.AbortarTransaccion();
            throw e;
        } finally {
            servicioDAO.CerrarSesion();
        }
        return true;
    }
    
    public List<Object[]> ObtenerServicioUnidades(String filtro) {
        List<Object[]> lista = new ArrayList<>();
        try {
            servicioUnidadDAO = new ServicioUnidadDAO();
            servicioUnidadDAO.AbrirSesion();
            lista = servicioUnidadDAO.ObtenerServicioUnidades(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            servicioUnidadDAO.CerrarSesion();
        }
        return lista;
    }
}
