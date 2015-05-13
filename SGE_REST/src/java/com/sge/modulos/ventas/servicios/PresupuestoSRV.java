package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Presupuesto;
import com.sge.modulos.ventas.negocios.PresupuestoDTO;
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
@Path("PresupuestoSRV")
public class PresupuestoSRV {
    
    @Context
    private UriInfo context;

    public PresupuestoSRV() {
    }

    @POST
    @Path("ObtenerPresupuestos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPresupuestos(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            List<Object[]> lista = PresupuestoDTO.ObtenerPresupuestos(filtro);
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
    @Path("ObtenerPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            Presupuesto presupuesto = PresupuestoDTO.ObtenerPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(presupuesto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        Presupuesto presupuesto = new Gson().fromJson(json, Presupuesto.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.RegistrarPresupuesto(presupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        Presupuesto presupuesto = new Gson().fromJson(json, Presupuesto.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.ActualizarPresupuesto(presupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.EliminarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
