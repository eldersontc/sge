package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Numeracion;
import com.sge.modulos.administracion.negocios.NumeracionDTO;
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
@Path("NumeracionSRV")
public class NumeracionSRV {
    
    @Context
    private UriInfo context;

    public NumeracionSRV() {
    }

    @POST
    @Path("ObtenerNumeraciones")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerNumeraciones(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            NumeracionDTO numeracionDTO = new NumeracionDTO();
            List<Numeracion> lista = numeracionDTO.ObtenerNumeraciones(filtro);
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
    @Path("ObtenerNumeracion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerNumeracion(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idNumeracion = new Gson().fromJson(json, int.class);
            NumeracionDTO numeracionDTO = new NumeracionDTO();
            Numeracion numeracion = numeracionDTO.ObtenerNumeracion(idNumeracion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(numeracion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarNumeracion")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarNumeracion(String json) {
        List<String> resultado = new ArrayList<>();
        Numeracion numeracion = new Gson().fromJson(json, Numeracion.class);
        try {
            NumeracionDTO numeracionDTO = new NumeracionDTO();
            numeracionDTO.RegistrarNumeracion(numeracion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarNumeracion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarNumeracion(String json) {
        List<String> resultado = new ArrayList<>();
        Numeracion numeracion = new Gson().fromJson(json, Numeracion.class);
        try {
            NumeracionDTO numeracionDTO = new NumeracionDTO();
            numeracionDTO.ActualizarNumeracion(numeracion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarNumeracion")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarNumeracion(String json) {
        List<String> resultado = new ArrayList<>();
        int idNumeracion = new Gson().fromJson(json, int.class);
        try {
            NumeracionDTO numeracionDTO = new NumeracionDTO();
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
