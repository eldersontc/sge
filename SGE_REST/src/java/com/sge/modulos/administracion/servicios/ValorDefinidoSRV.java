package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import com.sge.modulos.administracion.negocios.ValorDefinidoDTO;
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
@Path("ValorDefinidoSRV")
public class ValorDefinidoSRV {
    
    @Context
    private UriInfo context;

    public ValorDefinidoSRV() {
    }

    @POST
    @Path("ObtenerValoresDefinidos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerValoresDefinidos(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            List<Object[]> lista = valoresDefinidosDTO.ObtenerValoresDefinidos();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerValorDefinido")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerValorDefinido(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idValoresDefinidos = new Gson().fromJson(json, int.class);
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            ValorDefinido valoresDefinidos = valoresDefinidosDTO.ObtenerValorDefinido(idValoresDefinidos);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(valoresDefinidos));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarValoresDefinidos")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarValoresDefinidos(String json) {
        List<String> resultado = new ArrayList<>();
        ValorDefinido valoresDefinidos = new Gson().fromJson(json, ValorDefinido.class);
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            valoresDefinidosDTO.RegistrarValorDefinido(valoresDefinidos);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarValoresDefinidos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarValoresDefinidos(String json) {
        List<String> resultado = new ArrayList<>();
        ValorDefinido valoresDefinidos = new Gson().fromJson(json, ValorDefinido.class);
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            valoresDefinidosDTO.ActualizarValorDefinido(valoresDefinidos);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarValoresDefinidos")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarValoresDefinidos(String json) {
        List<String> resultado = new ArrayList<>();
        int idValoresDefinidos = new Gson().fromJson(json, int.class);
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            valoresDefinidosDTO.EliminarValorDefinido(idValoresDefinidos);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}