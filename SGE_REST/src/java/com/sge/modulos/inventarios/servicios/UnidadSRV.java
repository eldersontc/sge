package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.Unidad;
import com.sge.modulos.inventarios.negocios.UnidadDTO;
import java.util.ArrayList;
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
@Path("UnidadSRV")
public class UnidadSRV {

    @Context
    private UriInfo context;

    public UnidadSRV() {
    }

    @POST
    @Path("ObtenerUnidades/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidades(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            UnidadDTO UnidadDTO = new UnidadDTO(idUsuario);
            List<Unidad> lista = UnidadDTO.ObtenerUnidades(filtro);
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
    @Path("RegistrarUnidad/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUnidad(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Unidad unidad = new Gson().fromJson(json, Unidad.class);
        try {
            UnidadDTO UnidadDTO = new UnidadDTO(idUsuario);
            UnidadDTO.RegistrarUnidad(unidad);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarUnidad/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarUnidad(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Unidad unidad = new Gson().fromJson(json, Unidad.class);
        try {
            UnidadDTO UnidadDTO = new UnidadDTO(idUsuario);
            UnidadDTO.ActualizarUnidad(unidad);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarUnidad/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUnidad(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidad = new Gson().fromJson(json, int.class);
        try {
            UnidadDTO UnidadDTO = new UnidadDTO(idUsuario);
            UnidadDTO.EliminarUnidad(idUnidad);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
