package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.FormaPago;
import com.sge.modulos.ventas.negocios.FormaPagoDTO;
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
@Path("FormaPagoSRV")
public class FormaPagoSRV {
    
    @Context
    private UriInfo context;

    public FormaPagoSRV() {
    }

    @POST
    @Path("ObtenerFormasPago")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerFormasPago(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO();
            List<FormaPago> lista = FormaPagoDTO.ObtenerFormasPago(filtro);
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
    @Path("RegistrarFormaPago")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarFormaPago(String json) {
        List<String> resultado = new ArrayList<>();
        FormaPago formaPago = new Gson().fromJson(json, FormaPago.class);
        try {
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO();
            FormaPagoDTO.RegistrarFormaPago(formaPago);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarFormaPago")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarFormaPago(String json) {
        List<String> resultado = new ArrayList<>();
        FormaPago formaPago = new Gson().fromJson(json, FormaPago.class);
        try {
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO();
            FormaPagoDTO.ActualizarFormaPago(formaPago);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarFormaPago")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarFormaPago(String json) {
        List<String> resultado = new ArrayList<>();
        int idFormaPago = new Gson().fromJson(json, int.class);
        try {
            FormaPagoDTO FormaPagoDTO = new FormaPagoDTO();
            FormaPagoDTO.EliminarFormaPago(idFormaPago);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
