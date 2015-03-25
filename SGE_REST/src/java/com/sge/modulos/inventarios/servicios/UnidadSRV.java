package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.Unidad;
import com.sge.modulos.inventarios.negocios.UnidadDTO;
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
@Path("UnidadSRV")
public class UnidadSRV {
    
    @Context
    private UriInfo context;

    public UnidadSRV() {
    }

    @POST
    @Path("ObtenerUnidades")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidades(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            UnidadDTO UnidadDTO = new UnidadDTO();
            List<Object[]> lista = UnidadDTO.ObtenerUnidades();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarUnidad")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUnidad(String json) {
        List<String> resultado = new ArrayList<>();
        Unidad unidad = new Gson().fromJson(json, Unidad.class);
        try {
            UnidadDTO UnidadDTO = new UnidadDTO();
            UnidadDTO.RegistrarUnidad(unidad);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarUnidad")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarUnidad(String json) {
        List<String> resultado = new ArrayList<>();
        Unidad unidad = new Gson().fromJson(json, Unidad.class);
        try {
            UnidadDTO UnidadDTO = new UnidadDTO();
            UnidadDTO.ActualizarUnidad(unidad);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarUnidad")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUnidad(String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidad = new Gson().fromJson(json, int.class);
        try {
            UnidadDTO UnidadDTO = new UnidadDTO();
            UnidadDTO.EliminarUnidad(idUnidad);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}
