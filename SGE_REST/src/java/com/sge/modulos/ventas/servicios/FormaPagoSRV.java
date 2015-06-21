package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.FormaPago;
import com.sge.modulos.ventas.negocios.FormaPagoDTO;
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
@Path("FormaPagoSRV")
public class FormaPagoSRV {

    @Context
    private UriInfo context;

    public FormaPagoSRV() {
    }

    @POST
    @Path("ObtenerFormasPago/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerFormasPago(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO(idUsuario);
            List<FormaPago> lista = FormaPagoDTO.ObtenerFormasPago(filtro);
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
    @Path("RegistrarFormaPago/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarFormaPago(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        FormaPago formaPago = new Gson().fromJson(json, FormaPago.class);
        try {
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO(idUsuario);
            FormaPagoDTO.RegistrarFormaPago(formaPago);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarFormaPago/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarFormaPago(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        FormaPago formaPago = new Gson().fromJson(json, FormaPago.class);
        try {
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO(idUsuario);
            FormaPagoDTO.ActualizarFormaPago(formaPago);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarFormaPago/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarFormaPago(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idFormaPago = new Gson().fromJson(json, int.class);
        try {
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO(idUsuario);
            FormaPagoDTO.EliminarFormaPago(idFormaPago);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
