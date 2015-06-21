package com.sge.modulos.produccion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.produccion.entidades.OrdenProduccion;
import com.sge.modulos.produccion.negocios.OrdenProduccionDTO;
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
@Path("OrdenProduccionSRV")
public class OrdenProduccionSRV {

    @Context
    private UriInfo context;

    public OrdenProduccionSRV() {
    }

    @POST
    @Path("ObtenerOrdenesProduccion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerOrdenesProduccion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            OrdenProduccionDTO OrdenProduccionDTO = new OrdenProduccionDTO(idUsuario);
            List<OrdenProduccion> lista = OrdenProduccionDTO.ObtenerOrdenesProduccion(filtro);
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
    @Path("ObtenerOrdenProduccion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerOrdenProduccion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idOrdenProduccion = new Gson().fromJson(json, int.class);
        try {
            OrdenProduccionDTO OrdenProduccionDTO = new OrdenProduccionDTO(idUsuario);
            OrdenProduccion ordenProduccion = OrdenProduccionDTO.ObtenerOrdenProduccion(idOrdenProduccion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(ordenProduccion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarOrdenProduccion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarOrdenProduccion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        OrdenProduccion ordenProduccion = new Gson().fromJson(json, OrdenProduccion.class);
        try {
            OrdenProduccionDTO OrdenProduccionDTO = new OrdenProduccionDTO(idUsuario);
            OrdenProduccionDTO.RegistrarOrdenProduccion(ordenProduccion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarOrdenProduccion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarOrdenProduccion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        OrdenProduccion ordenProduccion = new Gson().fromJson(json, OrdenProduccion.class);
        try {
            OrdenProduccionDTO OrdenProduccionDTO = new OrdenProduccionDTO(idUsuario);
            OrdenProduccionDTO.ActualizarOrdenProduccion(ordenProduccion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarOrdenProduccion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarOrdenProduccion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idOrdenProduccion = new Gson().fromJson(json, int.class);
        try {
            OrdenProduccionDTO OrdenProduccionDTO = new OrdenProduccionDTO(idUsuario);
            OrdenProduccionDTO.EliminarOrdenProduccion(idOrdenProduccion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
