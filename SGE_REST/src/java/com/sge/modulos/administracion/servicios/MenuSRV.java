package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
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
    @Path("ObtenerMenus")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMenus(String json) {
        List<String> resultado = new ArrayList<>();
        int idUsuario = new Gson().fromJson(json, int.class);
        try {
            MenuDTO menuDTO = new MenuDTO();
            List<Object[]> lista = menuDTO.ObtenerMenus(idUsuario);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}
