package com.sge.modulos.finanzas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.finanzas.entidades.Caja;
import com.sge.modulos.finanzas.negocios.CajaDTO;
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
@Path("CajaSRV")
public class CajaSRV {
    
    @Context
    private UriInfo context;

    public CajaSRV() {
    }

    @POST
    @Path("ObtenerCajas")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCajas(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            CajaDTO CajaDTO = new CajaDTO();
            List<Caja> lista = CajaDTO.ObtenerCajas(filtro);
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
    @Path("RegistrarCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarCaja(String json) {
        List<String> resultado = new ArrayList<>();
        Caja caja = new Gson().fromJson(json, Caja.class);
        try {
            CajaDTO CajaDTO = new CajaDTO();
            CajaDTO.RegistrarCaja(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarCaja(String json) {
        List<String> resultado = new ArrayList<>();
        Caja caja = new Gson().fromJson(json, Caja.class);
        try {
            CajaDTO CajaDTO = new CajaDTO();
            CajaDTO.ActualizarCaja(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarCaja(String json) {
        List<String> resultado = new ArrayList<>();
        int idCaja = new Gson().fromJson(json, int.class);
        try {
            CajaDTO CajaDTO = new CajaDTO();
            CajaDTO.EliminarCaja(idCaja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
