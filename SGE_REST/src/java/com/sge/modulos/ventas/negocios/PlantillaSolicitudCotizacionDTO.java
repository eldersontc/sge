package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.ItemPlantillaSolicitudCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.PlantillaSolicitudCotizacionDAO;
import com.sge.modulos.ventas.entidades.ItemPlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.entidades.PlantillaSolicitudCotizacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class PlantillaSolicitudCotizacionDTO {
 
    PlantillaSolicitudCotizacionDAO plantillaSolicitudCotizacionDAO;
    ItemPlantillaSolicitudCotizacionDAO itemPlantillaSolicitudCotizacionDAO;
    
    public List<Object[]> ObtenerPlantillasSolicitudCotizacion(String filtro) {
        List<Object[]> lista;
        try {
            plantillaSolicitudCotizacionDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaSolicitudCotizacionDAO.AbrirSesion();
            lista = plantillaSolicitudCotizacionDAO.ObtenerPlantillasSolicitudCotizacion(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            plantillaSolicitudCotizacionDAO.CerrarSesion();
        }
        return lista;
    }

    public PlantillaSolicitudCotizacion ObtenerPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion) {
        PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = null;
        try {
            plantillaSolicitudCotizacionDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaSolicitudCotizacionDAO.AbrirSesion();
            plantillaSolicitudCotizacion = plantillaSolicitudCotizacionDAO.ObtenerPorId(PlantillaSolicitudCotizacion.class, idPlantillaSolicitudCotizacion);
            
            itemPlantillaSolicitudCotizacionDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaSolicitudCotizacionDAO.AsignarSesion(plantillaSolicitudCotizacionDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idPlantillaSolicitudCotizacion", idPlantillaSolicitudCotizacion});
            List<ItemPlantillaSolicitudCotizacion> items = itemPlantillaSolicitudCotizacionDAO.ObtenerLista(ItemPlantillaSolicitudCotizacion.class, filtros);
            plantillaSolicitudCotizacion.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            plantillaSolicitudCotizacionDAO.CerrarSesion();
        }
        return plantillaSolicitudCotizacion;
    }

    public boolean RegistrarPlantillaSolicitudCotizacion(PlantillaSolicitudCotizacion plantillaSolicitudCotizacion) {
        try {
            plantillaSolicitudCotizacionDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaSolicitudCotizacionDAO.IniciarTransaccion();
            plantillaSolicitudCotizacionDAO.Agregar(plantillaSolicitudCotizacion);

            itemPlantillaSolicitudCotizacionDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaSolicitudCotizacionDAO.AsignarSesion(plantillaSolicitudCotizacionDAO);
            for (ItemPlantillaSolicitudCotizacion item : plantillaSolicitudCotizacion.getItems()) {
                item.setIdPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion.getIdPlantillaSolicitudCotizacion());
                itemPlantillaSolicitudCotizacionDAO.Agregar(item);
            }

            plantillaSolicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            plantillaSolicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            plantillaSolicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarPlantillaSolicitudCotizacion(PlantillaSolicitudCotizacion plantillaSolicitudCotizacion) {
        try {
            plantillaSolicitudCotizacionDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaSolicitudCotizacionDAO.IniciarTransaccion();
            plantillaSolicitudCotizacionDAO.ActualizarPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion.getIdPlantillaSolicitudCotizacion(), plantillaSolicitudCotizacion.getNombre(), plantillaSolicitudCotizacion.getLineaProduccion(), plantillaSolicitudCotizacion.isActivo());

            itemPlantillaSolicitudCotizacionDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaSolicitudCotizacionDAO.AsignarSesion(plantillaSolicitudCotizacionDAO);
            for (ItemPlantillaSolicitudCotizacion item : plantillaSolicitudCotizacion.getItems()) {
                if(item.isAgregar()){
                    item.setIdPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion.getIdPlantillaSolicitudCotizacion());
                    itemPlantillaSolicitudCotizacionDAO.Agregar(item);
                }
                if(item.isActualizar()){
                    itemPlantillaSolicitudCotizacionDAO.ActualizarItemPlantillaSolicitudCotizacion(item);
                }
                if(item.isEliminar()){
                    itemPlantillaSolicitudCotizacionDAO.EliminarItemPlantillaSolicitudCotizacion(item.getIdItemPlantillaSolicitudCotizacion());
                }
            }

            plantillaSolicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            plantillaSolicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            plantillaSolicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion) {
        try {
            plantillaSolicitudCotizacionDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaSolicitudCotizacionDAO.IniciarTransaccion();
            plantillaSolicitudCotizacionDAO.EliminarPlantillaSolicitudCotizacion(idPlantillaSolicitudCotizacion);

            itemPlantillaSolicitudCotizacionDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaSolicitudCotizacionDAO.AsignarSesion(plantillaSolicitudCotizacionDAO);
            itemPlantillaSolicitudCotizacionDAO.EliminarItemPlantillaSolicitudCotizacionPorIdPlantillaSolicitudCotizacion(idPlantillaSolicitudCotizacion);

            plantillaSolicitudCotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            plantillaSolicitudCotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            plantillaSolicitudCotizacionDAO.CerrarSesion();
        }
        return true;
    }
}
