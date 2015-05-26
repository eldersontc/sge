package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Menu;
import com.sge.modulos.administracion.negocios.MenuDTO;
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
            MenuDTO menuDTO = new MenuDTO();
            List<Menu> lista = menuDTO.ObtenerMenusPorUsuario(idUsuario);
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
    @Path("ObtenerMenusPorPerfil")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMenusPorPerfil(String json) {
        List<String> resultado = new ArrayList<>();
        int idPerfil = new Gson().fromJson(json, int.class);
        try {
            MenuDTO menuDTO = new MenuDTO();
            List<Menu> lista = menuDTO.ObtenerMenusPorPerfil(idPerfil);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
