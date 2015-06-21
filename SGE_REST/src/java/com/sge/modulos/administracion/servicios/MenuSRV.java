package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Menu;
import com.sge.modulos.administracion.negocios.MensajeDTO;
import com.sge.modulos.administracion.negocios.MenuDTO;
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
@Path("MenuSRV")
public class MenuSRV {

    @Context
    private UriInfo context;

    public MenuSRV() {
    }

    @POST
    @Path("ObtenerMenusPorUsuario")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMenusPorUsuario(String json) {
        List<String> resultado = new ArrayList<>();
        int idUsuario = new Gson().fromJson(json, int.class);
        try {
            MenuDTO menuDTO = new MenuDTO(idUsuario);
            List<Menu> lista = menuDTO.ObtenerMenusPorUsuario();
            MensajeDTO mensajeDTO = new MensajeDTO(idUsuario);
            int mensajesSinLeer = mensajeDTO.ObtenerMensajesSinLeer();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
            resultado.add(new Gson().toJson(mensajesSinLeer));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerMenusPorPerfil/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMenusPorPerfil(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idPerfil = new Gson().fromJson(json, int.class);
        try {
            MenuDTO menuDTO = new MenuDTO(idUsuario);
            List<Menu> lista = menuDTO.ObtenerMenusPorPerfil(idPerfil);
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
    @Path("ActualizarPermisos/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPermisos(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        String[] arrayJson = new Gson().fromJson(json, String[].class);
        Menu[] menus = new Gson().fromJson(arrayJson[0], Menu[].class);
        int idPerfil = new Gson().fromJson(arrayJson[1], int.class);
        try {
            MenuDTO menuDTO = new MenuDTO(idUsuario);
            menuDTO.ActualizarPermisos(menus, idPerfil);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
