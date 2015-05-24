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
 
    PlantillaSolicitudCotizacionDAO plantillaDAO;
    ItemPlantillaSolicitudCotizacionDAO itemPlantillaDAO;
    
    public List<PlantillaSolicitudCotizacion> ObtenerPlantillasSolicitudCotizacion(String filtro) {
        List<PlantillaSolicitudCotizacion> lista;
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.AbrirSesion();
            lista = plantillaDAO.ObtenerPlantillasSolicitudCotizacion(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return lista;
    }

    public PlantillaSolicitudCotizacion ObtenerPlantillaSolicitudCotizacion(int idPlantilla) {
        PlantillaSolicitudCotizacion plantilla = null;
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.AbrirSesion();
            plantilla = plantillaDAO.ObtenerPorId(PlantillaSolicitudCotizacion.class, idPlantilla);
            
            itemPlantillaDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaDAO.AsignarSesion(plantillaDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idPlantillaSolicitudCotizacion", idPlantilla});
            List<ItemPlantillaSolicitudCotizacion> items = itemPlantillaDAO.ObtenerLista(ItemPlantillaSolicitudCotizacion.class, filtros);
            plantilla.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return plantilla;
    }

    public boolean RegistrarPlantillaSolicitudCotizacion(PlantillaSolicitudCotizacion plantilla) {
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.IniciarTransaccion();
            plantillaDAO.Agregar(plantilla);

            itemPlantillaDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaDAO.AsignarSesion(plantillaDAO);
            for (ItemPlantillaSolicitudCotizacion item : plantilla.getItems()) {
                item.setIdPlantillaSolicitudCotizacion(plantilla.getIdPlantillaSolicitudCotizacion());
                itemPlantillaDAO.Agregar(item);
            }

            plantillaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            plantillaDAO.AbortarTransaccion();
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarPlantillaSolicitudCotizacion(PlantillaSolicitudCotizacion plantilla) {
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.IniciarTransaccion();
            plantillaDAO.ActualizarPlantillaSolicitudCotizacion(plantilla.getIdPlantillaSolicitudCotizacion(), plantilla.getNombre(), plantilla.getLineaProduccion(), plantilla.isActivo());

            itemPlantillaDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaDAO.AsignarSesion(plantillaDAO);
            for (ItemPlantillaSolicitudCotizacion item : plantilla.getItems()) {
                if(item.isAgregar()){
                    item.setIdPlantillaSolicitudCotizacion(plantilla.getIdPlantillaSolicitudCotizacion());
                    itemPlantillaDAO.Agregar(item);
                }
                if(item.isActualizar()){
                    itemPlantillaDAO.ActualizarItemPlantillaSolicitudCotizacion(item);
                }
                if(item.isEliminar()){
                    itemPlantillaDAO.EliminarItemPlantillaSolicitudCotizacion(item.getIdItemPlantillaSolicitudCotizacion());
                }
            }

            plantillaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            plantillaDAO.AbortarTransaccion();
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarPlantillaSolicitudCotizacion(int idPlantilla) {
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.IniciarTransaccion();
            plantillaDAO.EliminarPlantillaSolicitudCotizacion(idPlantilla);

            itemPlantillaDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaDAO.AsignarSesion(plantillaDAO);
            itemPlantillaDAO.EliminarItemPlantillaSolicitudCotizacionPorIdPlantillaSolicitudCotizacion(idPlantilla);

            plantillaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            plantillaDAO.AbortarTransaccion();
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return true;
    }
    
    public int ObtenerGrupo() {
        int grupo = 0;
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.AbrirSesion();
            grupo = plantillaDAO.ObtenerGrupo();
        } catch (Exception e) {
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return grupo;
    }
}
