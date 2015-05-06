package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.MetodoImpresion;
import com.sge.modulos.ventas.negocios.MetodoImpresionDTO;
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
@Path("MetodoImpresionSRV")
public class MetodoImpresionSRV {
    
    @Context
    private UriInfo context;

    public MetodoImpresionSRV() {
    }

    @POST
    @Path("ObtenerMetodosImpresion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMetodosImpresion(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO();
            List<Object[]> lista = MetodoImpresionDTO.ObtenerMetodosImpresion(filtro);
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
    @Path("RegistrarMetodoImpresion")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMetodoImpresion(String json) {
        List<String> resultado = new ArrayList<>();
        MetodoImpresion metodoImpresion = new Gson().fromJson(json, MetodoImpresion.class);
        try {
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO();
            MetodoImpresionDTO.RegistrarMetodoImpresion(metodoImpresion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarMetodoImpresion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarMetodoImpresion(String json) {
        List<String> resultado = new ArrayList<>();
        MetodoImpresion metodoImpresion = new Gson().fromJson(json, MetodoImpresion.class);
        try {
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO();
            MetodoImpresionDTO.ActualizarMetodoImpresion(metodoImpresion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarMetodoImpresion")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarMetodoImpresion(String json) {
        List<String> resultado = new ArrayList<>();
        int idMetodoImpresion = new Gson().fromJson(json, int.class);
        try {
            MetodoImpresionDTO MetodoImpresionDTO = new MetodoImpresionDTO();
            MetodoImpresionDTO.EliminarMetodoImpresion(idMetodoImpresion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
