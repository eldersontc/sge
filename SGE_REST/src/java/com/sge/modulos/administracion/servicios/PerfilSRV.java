package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Perfil;
import com.sge.modulos.administracion.negocios.PerfilDTO;
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
@Path("PerfilSRV")
public class PerfilSRV {

    @Context
    private UriInfo context;

    public PerfilSRV() {
    }

    @POST
    @Path("ObtenerPerfiles/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPerfiles(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            PerfilDTO perfilDTO = new PerfilDTO(idUsuario);
            List<Perfil> lista = perfilDTO.ObtenerPerfiles(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
            resultado.add(new Gson().toJson(new Date()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarPerfil/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarPerfil(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Perfil perfil = new Gson().fromJson(json, Perfil.class);
        try {
            PerfilDTO perfilDTO = new PerfilDTO(idUsuario);
            perfilDTO.RegistrarPerfil(perfil);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarPerfil/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPerfil(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Perfil perfil = new Gson().fromJson(json, Perfil.class);
        try {
            PerfilDTO perfilDTO = new PerfilDTO(idUsuario);
            perfilDTO.ActualizarPerfil(perfil);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarPerfil/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarPerfil(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idPerfil = new Gson().fromJson(json, int.class);
        try {
            PerfilDTO perfilDTO = new PerfilDTO(idUsuario);
            perfilDTO.EliminarPerfil(idPerfil);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
