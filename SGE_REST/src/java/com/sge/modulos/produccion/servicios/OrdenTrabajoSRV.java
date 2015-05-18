package com.sge.modulos.produccion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.produccion.entidades.OrdenTrabajo;
import com.sge.modulos.produccion.negocios.OrdenTrabajoDTO;
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
@Path("OrdenTrabajoSRV")
public class OrdenTrabajoSRV {
    
    @Context
    private UriInfo context;

    public OrdenTrabajoSRV() {
    }

    @POST
    @Path("ObtenerOrdenesTrabajo")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerOrdenesTrabajo(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO();
            List<OrdenTrabajo> lista = OrdenTrabajoDTO.ObtenerOrdenesTrabajo(filtro);
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
    @Path("ObtenerOrdenTrabajo")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerOrdenTrabajo(String json) {
        List<String> resultado = new ArrayList<>();
        int idOrdenTrabajo = new Gson().fromJson(json, int.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO();
            OrdenTrabajo ordenTrabajo = OrdenTrabajoDTO.ObtenerOrdenTrabajo(idOrdenTrabajo);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(ordenTrabajo));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarOrdenTrabajo")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarOrdenTrabajo(String json) {
        List<String> resultado = new ArrayList<>();
        OrdenTrabajo ordenTrabajo = new Gson().fromJson(json, OrdenTrabajo.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO();
            OrdenTrabajoDTO.RegistrarOrdenTrabajo(ordenTrabajo);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarOrdenTrabajo")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarOrdenTrabajo(String json) {
        List<String> resultado = new ArrayList<>();
        OrdenTrabajo ordenTrabajo = new Gson().fromJson(json, OrdenTrabajo.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO();
            OrdenTrabajoDTO.ActualizarOrdenTrabajo(ordenTrabajo);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarOrdenTrabajo")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarOrdenTrabajo(String json) {
        List<String> resultado = new ArrayList<>();
        int idOrdenTrabajo = new Gson().fromJson(json, int.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO();
            OrdenTrabajoDTO.EliminarOrdenTrabajo(idOrdenTrabajo);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
