package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Moneda;
import com.sge.modulos.administracion.negocios.MonedaDTO;
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
@Path("MonedaSRV")
public class MonedaSRV {
    
    @Context
    private UriInfo context;

    public MonedaSRV() {
    }

    @POST
    @Path("ObtenerMonedas")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMonedas(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            MonedaDTO monedaDTO = new MonedaDTO();
            List<Object[]> lista = monedaDTO.ObtenerMonedas();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarMoneda")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMoneda(String json) {
        List<String> resultado = new ArrayList<>();
        Moneda moneda = new Gson().fromJson(json, Moneda.class);
        try {
            MonedaDTO monedaDTO = new MonedaDTO();
            monedaDTO.RegistrarMoneda(moneda);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarMoneda")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarMoneda(String json) {
        List<String> resultado = new ArrayList<>();
        Moneda moneda = new Gson().fromJson(json, Moneda.class);
        try {
            MonedaDTO monedaDTO = new MonedaDTO();
            monedaDTO.ActualizarMoneda(moneda);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarMoneda")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarMoneda(String json) {
        List<String> resultado = new ArrayList<>();
        int idMoneda = new Gson().fromJson(json, int.class);
        try {
            MonedaDTO monedaDTO = new MonedaDTO();
            monedaDTO.EliminarMoneda(idMoneda);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}
