package com.sge.modulos.finanzas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.finanzas.entidades.SalidaCaja;
import com.sge.modulos.finanzas.negocios.SalidaCajaDTO;
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
@Path("SalidaCajaSRV")
public class SalidaCajaSRV {

    @Context
    private UriInfo context;

    public SalidaCajaSRV() {
    }

    @POST
    @Path("ObtenerSalidasCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSalidasCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            SalidaCajaDTO SalidaCajaDTO = new SalidaCajaDTO(idUsuario);
            List<SalidaCaja> lista = SalidaCajaDTO.ObtenerSalidasCaja(filtro);
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
    @Path("ObtenerSalidaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSalidaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idSalidaCaja = new Gson().fromJson(json, int.class);
        try {
            SalidaCajaDTO entradaInventarioDTO = new SalidaCajaDTO(idUsuario);
            SalidaCaja entradaInventario = entradaInventarioDTO.ObtenerSalidaCaja(idSalidaCaja);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(entradaInventario));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarSalidaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarSalidaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        SalidaCaja caja = new Gson().fromJson(json, SalidaCaja.class);
        try {
            SalidaCajaDTO SalidaCajaDTO = new SalidaCajaDTO(idUsuario);
            SalidaCajaDTO.RegistrarSalidaCaja(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarSalidaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarSalidaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idSalidaCaja = new Gson().fromJson(json, int.class);
        try {
            SalidaCajaDTO SalidaCajaDTO = new SalidaCajaDTO(idUsuario);
            SalidaCajaDTO.EliminarSalidaCaja(idSalidaCaja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
