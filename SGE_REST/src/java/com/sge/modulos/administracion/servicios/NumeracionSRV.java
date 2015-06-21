package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Numeracion;
import com.sge.modulos.administracion.negocios.NumeracionDTO;
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
@Path("NumeracionSRV")
public class NumeracionSRV {

    @Context
    private UriInfo context;

    public NumeracionSRV() {
    }

    @POST
    @Path("ObtenerNumeraciones/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerNumeraciones(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            NumeracionDTO numeracionDTO = new NumeracionDTO(idUsuario);
            List<Numeracion> lista = numeracionDTO.ObtenerNumeraciones(filtro);
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
    @Path("ObtenerNumeracion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerNumeracion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idNumeracion = new Gson().fromJson(json, int.class);
            NumeracionDTO numeracionDTO = new NumeracionDTO(idUsuario);
            Numeracion numeracion = numeracionDTO.ObtenerNumeracion(idNumeracion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(numeracion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarNumeracion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarNumeracion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Numeracion numeracion = new Gson().fromJson(json, Numeracion.class);
        try {
            NumeracionDTO numeracionDTO = new NumeracionDTO(idUsuario);
            numeracionDTO.RegistrarNumeracion(numeracion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarNumeracion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarNumeracion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Numeracion numeracion = new Gson().fromJson(json, Numeracion.class);
        try {
            NumeracionDTO numeracionDTO = new NumeracionDTO(idUsuario);
            numeracionDTO.ActualizarNumeracion(numeracion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarNumeracion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarNumeracion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idNumeracion = new Gson().fromJson(json, int.class);
        try {
            NumeracionDTO numeracionDTO = new NumeracionDTO(idUsuario);
            numeracionDTO.EliminarNumeracion(idNumeracion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
