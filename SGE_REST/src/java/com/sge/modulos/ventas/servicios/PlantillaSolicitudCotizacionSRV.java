package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import com.sge.modulos.administracion.negocios.ValorDefinidoDTO;
import com.sge.modulos.ventas.entidades.PlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.negocios.PlantillaSolicitudCotizacionDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Path("ObtenerPlantillasSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPlantillasSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO(idUsuario);
            List<PlantillaSolicitudCotizacion> lista = PlantillaSolicitudCotizacionDTO.ObtenerPlantillasSolicitudCotizacion(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerPlantillasSolicitudCotizacionConItems/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPlantillasSolicitudCotizacionConItems(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            PlantillaSolicitudCotizacion[] plantillas = new Gson().fromJson(json, PlantillaSolicitudCotizacion[].class);
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO(idUsuario);
            PlantillaSolicitudCotizacion[] lista = PlantillaSolicitudCotizacionDTO.ObtenerPlantillasSolicitudCotizacionConItems(plantillas);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerPlantillaSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPlantillaSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idPlantillaSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO(idUsuario);
            PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = PlantillaSolicitudCotizacionDTO.ObtenerPlantillaSolicitudCotizacion(idPlantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(plantillaSolicitudCotizacion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarPlantillaSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarPlantillaSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = new Gson().fromJson(json, PlantillaSolicitudCotizacion.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO(idUsuario);
            PlantillaSolicitudCotizacionDTO.RegistrarPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarPlantillaSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPlantillaSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        PlantillaSolicitudCotizacion plantillaSolicitudCotizacion = new Gson().fromJson(json, PlantillaSolicitudCotizacion.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO(idUsuario);
            PlantillaSolicitudCotizacionDTO.ActualizarPlantillaSolicitudCotizacion(plantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarPlantillaSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarPlantillaSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idPlantillaSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            PlantillaSolicitudCotizacionDTO PlantillaSolicitudCotizacionDTO = new PlantillaSolicitudCotizacionDTO(idUsuario);
            PlantillaSolicitudCotizacionDTO.EliminarPlantillaSolicitudCotizacion(idPlantillaSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
