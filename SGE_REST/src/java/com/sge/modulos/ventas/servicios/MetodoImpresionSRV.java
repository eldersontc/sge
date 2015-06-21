package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.MetodoImpresion;
import com.sge.modulos.ventas.negocios.MetodoImpresionDTO;
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
@Path("MetodoImpresionSRV")
public class MetodoImpresionSRV {

    @Context
    private UriInfo context;

    public MetodoImpresionSRV() {
    }

    @POST
    @Path("ObtenerMetodosImpresion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMetodosImpresion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO(idUsuario);
            List<MetodoImpresion> lista = MetodoImpresionDTO.ObtenerMetodosImpresion(filtro);
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
    @Path("RegistrarMetodoImpresion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMetodoImpresion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        MetodoImpresion metodoImpresion = new Gson().fromJson(json, MetodoImpresion.class);
        try {
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO(idUsuario);
            MetodoImpresionDTO.RegistrarMetodoImpresion(metodoImpresion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarMetodoImpresion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarMetodoImpresion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        MetodoImpresion metodoImpresion = new Gson().fromJson(json, MetodoImpresion.class);
        try {
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO(idUsuario);
            MetodoImpresionDTO.ActualizarMetodoImpresion(metodoImpresion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarMetodoImpresion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarMetodoImpresion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idMetodoImpresion = new Gson().fromJson(json, int.class);
        try {
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO(idUsuario);
            MetodoImpresionDTO.EliminarMetodoImpresion(idMetodoImpresion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
