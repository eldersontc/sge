package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Moneda;
import com.sge.modulos.administracion.negocios.MonedaDTO;
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
@Path("MonedaSRV")
public class MonedaSRV {

    @Context
    private UriInfo context;

    public MonedaSRV() {
    }

    @POST
    @Path("ObtenerMonedas/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMonedas(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            MonedaDTO monedaDTO = new MonedaDTO(idUsuario);
            List<Moneda> lista = monedaDTO.ObtenerMonedas(filtro);
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
    @Path("RegistrarMoneda/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMoneda(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Moneda moneda = new Gson().fromJson(json, Moneda.class);
        try {
            MonedaDTO monedaDTO = new MonedaDTO(idUsuario);
            monedaDTO.RegistrarMoneda(moneda);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarMoneda/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarMoneda(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Moneda moneda = new Gson().fromJson(json, Moneda.class);
        try {
            MonedaDTO monedaDTO = new MonedaDTO(idUsuario);
            monedaDTO.ActualizarMoneda(moneda);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarMoneda/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarMoneda(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idMoneda = new Gson().fromJson(json, int.class);
        try {
            MonedaDTO monedaDTO = new MonedaDTO(idUsuario);
            monedaDTO.EliminarMoneda(idMoneda);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
