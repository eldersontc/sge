package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Distrito;
import com.sge.modulos.administracion.negocios.DistritoDTO;
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
@Path("DistritoSRV")
public class DistritoSRV {
    
    @Context
    private UriInfo context;

    public DistritoSRV() {
    }

    @POST
    @Path("ObtenerDistritos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerDistritos(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            DistritoDTO distritoDTO = new DistritoDTO();
            List<Distrito> lista = distritoDTO.ObtenerDistritos(filtro);
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
