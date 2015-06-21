package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Entidad;
import com.sge.modulos.administracion.negocios.EntidadDTO;
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
@Path("EntidadSRV")
public class EntidadSRV {

    @Context
    private UriInfo context;

    public EntidadSRV() {
    }

    @POST
    @Path("ObtenerEntidades/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntidades(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            EntidadDTO entidadDTO = new EntidadDTO(idUsuario);
            List<Entidad> lista = entidadDTO.ObtenerEntidades(filtro);
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
    @Path("ObtenerEntidad/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntidad(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idEntidad = new Gson().fromJson(json, int.class);
            EntidadDTO entidadDTO = new EntidadDTO(idUsuario);
            Entidad entidad = entidadDTO.ObtenerEntidad(idEntidad);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(entidad));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
