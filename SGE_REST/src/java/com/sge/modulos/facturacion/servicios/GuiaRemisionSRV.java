package com.sge.modulos.facturacion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.facturacion.entidades.GuiaRemision;
import com.sge.modulos.facturacion.negocios.GuiaRemisionDTO;
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
@Path("GuiaRemisionSRV")
public class GuiaRemisionSRV {
    
    @Context
    private UriInfo context;

    public GuiaRemisionSRV() {
    }

    @POST
    @Path("ObtenerGuiasRemision")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerGuiasRemision(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            GuiaRemisionDTO GuiaRemisionDTO = new GuiaRemisionDTO();
            List<GuiaRemision> lista = GuiaRemisionDTO.ObtenerGuiasRemision(filtro);
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
    @Path("ObtenerGuiaRemision")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerGuiaRemision(String json) {
        List<String> resultado = new ArrayList<>();
        int idGuiaRemision = new Gson().fromJson(json, int.class);
        try {
            GuiaRemisionDTO GuiaRemisionDTO = new GuiaRemisionDTO();
            GuiaRemision guiaRemision = GuiaRemisionDTO.ObtenerGuiaRemision(idGuiaRemision);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(guiaRemision));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarGuiaRemision")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarGuiaRemision(String json) {
        List<String> resultado = new ArrayList<>();
        GuiaRemision guiaRemision = new Gson().fromJson(json, GuiaRemision.class);
        try {
            GuiaRemisionDTO GuiaRemisionDTO = new GuiaRemisionDTO();
            GuiaRemisionDTO.RegistrarGuiaRemision(guiaRemision);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarGuiaRemision")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarGuiaRemision(String json) {
        List<String> resultado = new ArrayList<>();
        GuiaRemision guiaRemision = new Gson().fromJson(json, GuiaRemision.class);
        try {
            GuiaRemisionDTO GuiaRemisionDTO = new GuiaRemisionDTO();
            GuiaRemisionDTO.ActualizarGuiaRemision(guiaRemision);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarGuiaRemision")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarGuiaRemision(String json) {
        List<String> resultado = new ArrayList<>();
        int idGuiaRemision = new Gson().fromJson(json, int.class);
        try {
            GuiaRemisionDTO GuiaRemisionDTO = new GuiaRemisionDTO();
            GuiaRemisionDTO.EliminarGuiaRemision(idGuiaRemision);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
