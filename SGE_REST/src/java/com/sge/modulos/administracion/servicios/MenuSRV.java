package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.negocios.MenuDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
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

    @GET
    @Path("ObtenerMenus")
    @Produces("application/json")
    public String ObtenerMenus(String json) {
        List<Object> resultado = new ArrayList<>();
        try {
            MenuDTO menuDTO = new MenuDTO();
            List<Object[]> lista = menuDTO.ObtenerMenus();
            resultado.add(true);
            resultado.add(lista);
        } catch (Exception e) {
            resultado.add(false);
        }
        return new Gson().toJson(resultado);
    }
}
