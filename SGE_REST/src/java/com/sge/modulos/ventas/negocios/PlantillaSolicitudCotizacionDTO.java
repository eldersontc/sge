package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
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
public class PlantillaSolicitudCotizacionDTO extends BaseDTO {

    PlantillaSolicitudCotizacionDAO plantillaDAO;
    ItemPlantillaSolicitudCotizacionDAO itemPlantillaDAO;

    public PlantillaSolicitudCotizacionDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<PlantillaSolicitudCotizacion> ObtenerPlantillasSolicitudCotizacion(String filtro) {
        List<PlantillaSolicitudCotizacion> lista;
        try {
            plantillaDAO = new PlantillaSolicitudCotizacionDAO();
            plantillaDAO.AbrirSesion();
            lista = plantillaDAO.ObtenerPlantillasSolicitudCotizacion(getFiltro(plantillaDAO, 31, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            plantillaDAO.CerrarSesion();
        }
        return lista;
    }

    public PlantillaSolicitudCotizacion[] ObtenerPlantillasSolicitudCotizacionConItems(PlantillaSolicitudCotizacion[] plantillas) {
        try {
            itemPlantillaDAO = new ItemPlantillaSolicitudCotizacionDAO();
            itemPlantillaDAO.AbrirSesion();
            
            for (PlantillaSolicitudCotizacion plantilla : plantillas) {
                List<Object[]> filtros = new ArrayList<>();
                filtros.add(new Object[]{"idPlantillaSolicitudCotizacion", plantilla.getIdPlantillaSolicitudCotizacion()});
                List<ItemPlantillaSolicitudCotizacion> items = itemPlantillaDAO.ObtenerLista(ItemPlantillaSolicitudCotizacion.class, filtros);
                plantilla.setItems(items);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            itemPlantillaDAO.CerrarSesion();
        }
        return plantillas;
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
                if (item.isAgregar()) {
                    item.setIdPlantillaSolicitudCotizacion(plantilla.getIdPlantillaSolicitudCotizacion());
                    itemPlantillaDAO.Agregar(item);
                }
                if (item.isActualizar()) {
                    itemPlantillaDAO.ActualizarItemPlantillaSolicitudCotizacion(item);
                }
                if (item.isEliminar()) {
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
}
