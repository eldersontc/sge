package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Filtro;
import com.sge.modulos.administracion.negocios.FiltroDTO;
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
@Path("FiltroSRV")
public class FiltroSRV {
    
    @Context
    private UriInfo context;

    public FiltroSRV() {
    }

    @POST
    @Path("ObtenerFiltros/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerFiltros(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            FiltroDTO filtroDTO = new FiltroDTO(idUsuario);
            List<Filtro> lista = filtroDTO.ObtenerFiltros(filtro);
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
    @Path("RegistrarFiltro/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarFiltro(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Filtro filtro = new Gson().fromJson(json, Filtro.class);
        try {
            FiltroDTO filtroDTO = new FiltroDTO(idUsuario);
            filtroDTO.RegistrarFiltro(filtro);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarFiltro/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarFiltro(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Filtro filtro = new Gson().fromJson(json, Filtro.class);
        try {
            FiltroDTO filtroDTO = new FiltroDTO(idUsuario);
            filtroDTO.ActualizarFiltro(filtro);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarFiltro/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarFiltro(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idFiltro = new Gson().fromJson(json, int.class);
        try {
            FiltroDTO filtroDTO = new FiltroDTO(idUsuario);
            filtroDTO.EliminarFiltro(idFiltro);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
