package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import com.sge.modulos.administracion.negocios.ValorDefinidoDTO;
import java.util.ArrayList;
import java.util.Date;
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
            String filtro = new Gson().fromJson(json, String.class);
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            List<ValorDefinido> lista = valoresDefinidosDTO.ObtenerValoresDefinidos(filtro);
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
    @Path("ObtenerValorDefinido")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerValorDefinido(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idValorDefinido = new Gson().fromJson(json, int.class);
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            ValorDefinido valoresDefinidos = valoresDefinidosDTO.ObtenerValorDefinido(idValorDefinido);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(valoresDefinidos));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarValorDefinido")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarValorDefinido(String json) {
        List<String> resultado = new ArrayList<>();
        ValorDefinido valoresDefinidos = new Gson().fromJson(json, ValorDefinido.class);
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            valoresDefinidosDTO.RegistrarValorDefinido(valoresDefinidos);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarValorDefinido")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarValorDefinido(String json) {
        List<String> resultado = new ArrayList<>();
        ValorDefinido valoresDefinidos = new Gson().fromJson(json, ValorDefinido.class);
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            valoresDefinidosDTO.ActualizarValorDefinido(valoresDefinidos);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarValorDefinido")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarValorDefinido(String json) {
        List<String> resultado = new ArrayList<>();
        int idValoresDefinidos = new Gson().fromJson(json, int.class);
        try {
            ValorDefinidoDTO valoresDefinidosDTO = new ValorDefinidoDTO();
            valoresDefinidosDTO.EliminarValorDefinido(idValoresDefinidos);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerValorDefinidoPorUsuarioYEntidad")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerValorDefinidoPorUsuarioYEntidad(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int[] ids = new Gson().fromJson(json, int[].class);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO();
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[0], ids[1]);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
