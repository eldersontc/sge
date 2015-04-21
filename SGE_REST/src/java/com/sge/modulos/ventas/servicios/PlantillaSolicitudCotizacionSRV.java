package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.PlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.negocios.PlantillaSolicitudCotizacionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author elderson
 */
@Path("PlantillaSolicitudCotizacionSRV")
public class PlantillaSolicitudCotizacionSRV {
    
    @Context
    private UriInfo context;

    public PlantillaSolicitudCotizacionSRV() {
    }

    @POST
    @Path("ObtenerPlantillasSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPlantillasSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO();
            List<Object[]> lista = PlantillaSolicitudCotizacionDTO.ObtenerPlantillasSolicitudCotizacion(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerPlantillaSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPlantillaSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int idPlantillaSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO();
            PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = PlantillaSolicitudCotizacionDTO.ObtenerPlantillaSolicitudCotizacion(idPlantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(plantillaSolicitudCotizacion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarPlantillaSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarPlantillaSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = new Gson().fromJson(json, PlantillaSolicitudCotizacion.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO();
            PlantillaSolicitudCotizacionDTO.RegistrarPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarPlantillaSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPlantillaSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = new Gson().fromJson(json, PlantillaSolicitudCotizacion.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO();
            PlantillaSolicitudCotizacionDTO.ActualizarPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarPlantillaSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarPlantillaSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int idPlantillaSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO();
            PlantillaSolicitudCotizacionDTO.EliminarPlantillaSolicitudCotizacion(idPlantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
