package com.sge.modulos.finanzas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.finanzas.entidades.SalidaCaja;
import com.sge.modulos.finanzas.negocios.SalidaCajaDTO;
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
@Path("SalidaCajaSRV")
public class SalidaCajaSRV {
    
    @Context
    private UriInfo context;

    public SalidaCajaSRV() {
    }

    @POST
    @Path("ObtenerSalidasCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSalidasCaja(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            SalidaCajaDTO SalidaCajaDTO = new SalidaCajaDTO();
            List<SalidaCaja> lista = SalidaCajaDTO.ObtenerSalidasCaja(filtro);
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
    @Path("ObtenerSalidaCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSalidaCaja(String json) {
        List<String> resultado = new ArrayList<>();
        int idSalidaCaja = new Gson().fromJson(json, int.class);
        try {
            SalidaCajaDTO entradaInventarioDTO = new SalidaCajaDTO();
            SalidaCaja entradaInventario = entradaInventarioDTO.ObtenerSalidaCaja(idSalidaCaja);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(entradaInventario));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarSalidaCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarSalidaCaja(String json) {
        List<String> resultado = new ArrayList<>();
        SalidaCaja caja = new Gson().fromJson(json, SalidaCaja.class);
        try {
            SalidaCajaDTO SalidaCajaDTO = new SalidaCajaDTO();
            SalidaCajaDTO.RegistrarSalidaCaja(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarSalidaCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarSalidaCaja(String json) {
        List<String> resultado = new ArrayList<>();
        int idSalidaCaja = new Gson().fromJson(json, int.class);
        try {
            SalidaCajaDTO SalidaCajaDTO = new SalidaCajaDTO();
            SalidaCajaDTO.EliminarSalidaCaja(idSalidaCaja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
